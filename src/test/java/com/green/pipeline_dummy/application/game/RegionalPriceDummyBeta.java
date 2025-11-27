package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.regional_price.RegionalPriceMapper;
import com.green.pipeline_dummy.application.regional_price.model.RegionalPriceDummyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegionalPriceDummyBeta extends MbDummy {
    @Autowired
    private RegionalPriceMapper regionalPriceMapper;
    @Autowired
    private GameMapper gameMapper;

    private BigDecimal generatePriceByCurrency(String currency) {

        switch (currency) {
            case "KRW": return BigDecimal.valueOf(faker.number().numberBetween(15000, 69000));
            case "JPY": return BigDecimal.valueOf(faker.number().numberBetween(1000, 9000));
            case "USD": return BigDecimal.valueOf(faker.number().numberBetween(5, 70));
            case "EUR": return BigDecimal.valueOf(faker.number().numberBetween(5, 70));
            case "GBP": return BigDecimal.valueOf(faker.number().numberBetween(4, 60));
            default:
                return BigDecimal.valueOf(faker.number().numberBetween(5, 70));
        }
    }

    @Test
    @Rollback(false)
    void saveRegionalPrice() {

        // 사용할 국가 리스트
        String[] countryList = {
                "AUS","CAN","DEU","FRA","GBR","JPN",
                "KOR","MYS","SGP","TWN","USA"
        };

        // 전체 게임 ID 조회
        List<Long> gameIds = gameMapper.findGameIds();

        for (Long gameId : gameIds) {

            for (String country : countryList) {

                // (1) 국가의 primary 통화 조회
                String currency = regionalPriceMapper.findPrimaryCurrency(country);
                if (currency == null) {
                    continue;
                }

                // (2) 가격 생성
                BigDecimal price = generatePriceByCurrency(currency);

                // (3) Insert DTO
                RegionalPriceDummyDto dto = RegionalPriceDummyDto.builder()
                        .countryCode(country)
                        .currencyCode(currency)
                        .gameId(gameId)
                        .priceAmount(price)
                        .build();

                // (4) 저장
                regionalPriceMapper.saveRegionalPrice(dto);
            }
        }

    }
}
