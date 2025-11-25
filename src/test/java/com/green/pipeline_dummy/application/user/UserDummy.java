package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.Dummy;
import com.green.pipeline_dummy.application.common.CommonCodeRepository;
import com.green.pipeline_dummy.application.common.CountryRepository;
import com.green.pipeline_dummy.entitiy.common.CommonCode;
import com.green.pipeline_dummy.entitiy.country.Country;
import com.green.pipeline_dummy.entitiy.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDummy extends Dummy {
    @Autowired UserRepository userRepository;
    @Autowired CountryRepository countryRepository;
    @Autowired CommonCodeRepository commonCodeRepository;

    @Test
    @Rollback(false)
    void saveDP(){
        final int SIZE = 70_000;
        CommonCode role = commonCodeRepository.findById("RO-DP").get();
        for(int i =0; i<SIZE; i++){
           userRepository.save(saveUserInfo(role)) ;
        }
        userRepository.flush();
    }



    User saveUserInfo(CommonCode roleType){
        String[] countryList = {"AUS","CAN","DEU","FRA","GBR","JPN","KOR","MYS","SGP","TWN","USA"};
        int random = (int)(Math.random()*countryList.length);
        Country co = countryRepository.findById(countryList[random]).orElse(null);

        Random random2 = new Random();
        StringBuilder userId = new StringBuilder();


        for (int i = 0; i < 10; i++) {
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
        String email = userId.toString()+"@example.com";

        User result = User.builder()
                .countryCode(co)
                .userName(userId.toString())
                .password("1234")
                .email(email)
                .userRole(roleType)
                .createdAt(randomDateFuture())
                .build();
        return result;
    }

    // 미래 임의 날짜
    private static Timestamp randomDateFuture() {
        LocalDate startDate = LocalDate.of(2020, 2, 1);
        LocalDate endDate = LocalDate.of(2025, 11, 1);

        LocalDateTime start = startDate.atStartOfDay(); // 2024-02-01 00:00
        LocalDateTime end = endDate.atTime(23, 59, 59); // 2025-11-01 23:59:59

        long startEpoch = start.toEpochSecond(java.time.ZoneOffset.UTC);
        long endEpoch = end.toEpochSecond(java.time.ZoneOffset.UTC);

        long randomEpoch = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
        LocalDateTime randomDateTime = LocalDateTime.ofEpochSecond(randomEpoch, 0, java.time.ZoneOffset.UTC);
        return Timestamp.valueOf(randomDateTime);
    }
    void test(){
        System.out.println(123);
    }
}
