package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.Dummy;
import com.green.pipeline_dummy.application.common.CommonCodeRepository;
import com.green.pipeline_dummy.application.common.CountryRepository;
import com.green.pipeline_dummy.entitiy.common.CommonCode;
import com.green.pipeline_dummy.entitiy.country.Country;
import com.green.pipeline_dummy.entitiy.user.GamerProfile;
import com.green.pipeline_dummy.entitiy.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDummy extends Dummy {
    @Autowired UserRepository userRepository;
    @Autowired GamerProfileRepository gamerProfileRepository;
    @Autowired CountryRepository countryRepository;
    @Autowired CommonCodeRepository commonCodeRepository;
    @Autowired private EntityManager em;

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

    @Test
    @Rollback(false)
    void saveGM(){
        final int SIZE = 200_000;
        CommonCode role = commonCodeRepository.findById("RO-GM").get();
        for(int i =0; i<SIZE; i++){
            userRepository.save(saveUserInfo(role)) ;
        }
        userRepository.flush();
    }


    // 유저 더미 입력
    User saveUserInfo(CommonCode roleType){
        String[] countryList = {"AUS","CAN","DEU","FRA","GBR","JPN","KOR","MYS","SGP","TWN","USA"};
        int random = (int)(Math.random()*countryList.length);
        Country co = countryRepository.findById(countryList[random]).orElse(null);
        String userId = createName(10);
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


    @Test
    @Rollback(false)
    void saveGamerProfile(){
        List<User> userList = userRepository.findAllByUserRole(commonCodeRepository.findById("RO-GM").get());

        for(User u : userList ){
            GamerProfile gp = GamerProfile.builder()
                    .user(u)
                    .gmNickname(u.getUserName())
                    .avatar(createName(50)+".png")
                    .profileBanner(createName(50)+".jpg")
                    .lastLogin(randomDateFuture())
                    .wallet(randomWallet(50000))
                .build();
            gamerProfileRepository.save(gp);
            gamerProfileRepository.flush();
            em.clear();

        }

    }


    // 미래 임의 날짜
    private static Timestamp randomDateFuture() {
        //LocalDate startDate = LocalDate.of(2020, 2, 1);
        LocalDate startDate = LocalDate.of(2025, 11, 2);
        LocalDate endDate = LocalDate.of(2025, 11, 24);

        LocalDateTime start = startDate.atStartOfDay(); // 2024-02-01 00:00
        LocalDateTime end = endDate.atTime(23, 59, 59); // 2025-11-01 23:59:59

        long startEpoch = start.toEpochSecond(java.time.ZoneOffset.UTC);
        long endEpoch = end.toEpochSecond(java.time.ZoneOffset.UTC);

        long randomEpoch = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
        LocalDateTime randomDateTime = LocalDateTime.ofEpochSecond(randomEpoch, 0, java.time.ZoneOffset.UTC);
        return Timestamp.valueOf(randomDateTime);
    }

    //랜덤 글자 생성
    String createName(int num){
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

    //랜덤 금액 생성
    public static BigDecimal randomWallet(long max) {
        // 0.00 ~ max.99 사이 랜덤 double 생성
        double randomValue = ThreadLocalRandom.current().nextDouble(0, max + 1);
        // BigDecimal로 변환 후 소수점 2자리로 반올림
        return BigDecimal.valueOf(randomValue).setScale(2, RoundingMode.HALF_UP);
    }
}
