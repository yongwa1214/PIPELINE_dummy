package com.green.pipeline_dummy.application.game;


import com.green.pipeline_dummy.application.common.CountryCurrencyRepository;
import com.green.pipeline_dummy.application.common.CountryRepository;
import com.green.pipeline_dummy.application.common.CurrencyRepository;


import com.green.pipeline_dummy.entity.country.Country;
import com.green.pipeline_dummy.entity.country.CountryCurrency;
import com.green.pipeline_dummy.entity.currency.Currency;
import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.RegionalPrice;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RegionalPriceDummy {

    private final GameRepository gameRepository;
    private final RegionalPriceRepository regionalPriceRepository;
    private final CountryRepository countryRepository;
    private final CurrencyRepository currencyRepository;
    private final CountryCurrencyRepository countryCurrencyRepository;

    private final Random random = new Random();


    @Transactional
    public void generateRegionalPrices() {

        List<Game> games = gameRepository.findAll();
        List<Country> countries = countryRepository.findAll();
        List<Currency> currencies = currencyRepository.findAll();

        for (Game game : games) {

            for (Country country : countries) {

                // 이 나라가 가진 통화 목록 가져오기
                List<CountryCurrency> ccList =
                        countryCurrencyRepository.findByCountry_CountryCode(country.getCountryCode());

                if (ccList.isEmpty()) {
                    continue;
                }

                Currency currency = ccList.get(random.nextInt(ccList.size())).getCurrency();

                double basePrice = 10 + random.nextInt(61);
                double multiplier = 0.7 + (random.nextDouble() * 1.5);

                BigDecimal finalPrice = BigDecimal.valueOf(basePrice * multiplier)
                        .setScale(2, BigDecimal.ROUND_HALF_UP);

                RegionalPrice rp = RegionalPrice.builder()
                        .country(country)
                        .currency(currency)
                        .game(game)
                        .priceAmount(finalPrice)
                        .build();

                regionalPriceRepository.save(rp);
            }
        }
    }
}
