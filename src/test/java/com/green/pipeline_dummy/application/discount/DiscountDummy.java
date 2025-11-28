package com.green.pipeline_dummy.application.discount;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community.ForumMapper;
import com.green.pipeline_dummy.application.discount.model.DiscountDummyDto;
import com.green.pipeline_dummy.application.game.GameMapper;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiscountDummy extends MbDummy {
    @Autowired
    private DiscountMapper discountMapper;
    @Autowired
    private GameMapper gameMapper;

    Faker faker = new Faker(Locale.KOREA);

    // 할인 더미 데이터
    @Test
    @Rollback(false)
    void saveDiscount() {
        final int SIZE = 300_000;

        List<Long> eventIds = discountMapper.findEventIds();
        List<Long> gameIds = gameMapper.findAllGameIds();
        for (int i = 0; i < SIZE; i++) {
            // 할인률 (10, 20, 30 .... 90)
            BigDecimal rate = BigDecimal.valueOf(faker.number().numberBetween(1, 10) * 10);

            DiscountDummyDto dto = DiscountDummyDto.builder()
                    .gameId(gameIds.get(faker.number().numberBetween(0, gameIds.size())))
                    .eventId(eventIds.get(faker.number().numberBetween(0, eventIds.size())))
                    .discountRate(rate)
                    .build();

            discountMapper.saveDiscount(dto);
        }
    }
}
