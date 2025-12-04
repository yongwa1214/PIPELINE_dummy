package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community.ForumMapper;
import com.green.pipeline_dummy.application.game.game_challenge.GameChallengeMapper;
import com.green.pipeline_dummy.application.game.game_challenge.model.UserChallengeDummyDto;
import com.green.pipeline_dummy.application.library.LibraryMapper;
import com.green.pipeline_dummy.application.purchase.purchase_item.PurchaseItemMapper;
import com.green.pipeline_dummy.model.RandomDate;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserChallengeDummy extends MbDummy {
    @Autowired GameChallengeMapper gameChallengeMapper;
    @Autowired LibraryMapper libraryMapper;
    @Autowired PurchaseItemMapper purchaseItemMapper;


    @Test
    @Rollback(false)
    void saveUserChallenge(){
        final int SIZE = 300_000;
        List<Long> libraryList = libraryMapper.findLibrary();
        for(int i = 0; i<SIZE; i++){
            Random r = new Random();
            long randomLibId = libraryList.get(r.nextInt(libraryList.size()));

            // 해당 libraryId의 game_id
            long gameId = purchaseItemMapper.findGameId(randomLibId);

            // 해당 game의 challenge 번호 목록
            List<Long> challengeList = gameChallengeMapper.findChallengeIds(gameId);

            //챌린지 셔플
            Collections.shuffle(challengeList);

            //선택해야 할 챌린지 수 (최소 1개)
            int max = challengeList.size();
            int pickCount = 1;

            if(max > 1){
                pickCount = 1 + (int)(Math.random() * (max - 1));
                // 1 ~ max-1
            }

            // 7. DTO 생성
            List<UserChallengeDummyDto> dtoList = new ArrayList<>();

            for(int j = 0; j < pickCount; j++){
                RandomDate randomDate = RandomDate.builder()
                        .startYear(2025)
                        .startMonth(1)
                        .startDate(1)
                        .endMonth(11)
                        .endDate(20)
                        .build();


                UserChallengeDummyDto dto = UserChallengeDummyDto.builder()
                        .libraryId(randomLibId)
                        .challengeId(challengeList.get(j))
                        .challengedAt(CommonMethod.randomDateFuture(randomDate))
                        .build();
                gameChallengeMapper.saveUserChallenge(dto);
            }
        }



    }
}
