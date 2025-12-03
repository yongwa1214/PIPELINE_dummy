package com.green.pipeline_dummy;

import com.green.pipeline_dummy.model.RandomDate;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonMethod {
    public static Faker faker = new Faker(new Locale("en")); // 영어

    public static LocalDateTime randomDateFuture(RandomDate value) {
        //LocalDate startDate = LocalDate.of(2020, 2, 1);
        LocalDate startDate = LocalDate.of(value.getStartYear(), value.getStartMonth(), value.getStartDate());
        LocalDate endDate = LocalDate.of(2025, value.getEndMonth(), value.getEndDate());

        LocalDateTime start = startDate.atStartOfDay(); // 2024-02-01 00:00
        LocalDateTime end = endDate.atTime(23, 59, 59); // 2025-11-01 23:59:59

        long startEpoch = start.toEpochSecond(java.time.ZoneOffset.UTC);
        long endEpoch = end.toEpochSecond(java.time.ZoneOffset.UTC);

        long randomEpoch = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
        LocalDateTime randomDateTime = LocalDateTime.ofEpochSecond(randomEpoch, 0, java.time.ZoneOffset.UTC);
        return randomDateTime;
    }

    public static String createName(int num){
        Random random2 = new Random();
        StringBuilder userId = new StringBuilder();


        for (int i = 0; i < num; i++) {
            int type = random2.nextInt(3); // 0: 대문자, 1: 소문자, 2: 숫자
            switch(type) {
                case 0:
                    userId.append((char)('A' + random2.nextInt(26))); // 대문자
                    break;
                case 1:
                    userId.append((char)('a' + random2.nextInt(26))); // 소문자
                    break;
                case 2:
                    userId.append(random2.nextInt(10)); // 숫자
                    break;
            }
        }
        return userId.toString();
    }

    public static BigDecimal walletCurrency(String currency) {

        switch (currency) {
            case "KRW": return BigDecimal.valueOf(faker.number().numberBetween(0, 10000));
            case "JPY": return BigDecimal.valueOf(faker.number().numberBetween(0, 1000));
            case "USD": return BigDecimal.valueOf(faker.number().numberBetween(0, 5));
            case "EUR": return BigDecimal.valueOf(faker.number().numberBetween(0, 5));
            case "GBP": return BigDecimal.valueOf(faker.number().numberBetween(0, 4));
            default:
                return BigDecimal.valueOf(faker.number().numberBetween(0, 5));
        }
    }
}
