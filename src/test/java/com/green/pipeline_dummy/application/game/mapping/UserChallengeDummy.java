package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community.ForumMapper;
import com.green.pipeline_dummy.application.game.game_challenge.GameChallengeMapper;
import com.green.pipeline_dummy.application.game.game_challenge.model.UserChallengeDummyDto;
import com.green.pipeline_dummy.application.library.LibraryMapper;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserChallengeDummy extends MbDummy {
    @Autowired
    private GameChallengeMapper gameChallengeMapper;
    @Autowired
    private LibraryMapper libraryMapper;
    @Autowired
    private ForumMapper forumMapper;

    Faker faker = new Faker(Locale.KOREA);

    @Test
    @Rollback(false)
    void saveUserChallenge() {
        final int SIZE = 2_000_000;

        List<Long> gamerIds = forumMapper.findGamerIds();

        // 유저별 라이브러리 미리 조회
        Map<Long, List<Long>> userLibraryMap = new HashMap<>();
        for (Long userId : gamerIds) {
            List<Long> libGames = libraryMapper.findLibraryIds(userId);
            if (!libGames.isEmpty()) userLibraryMap.put(userId, libGames);
        }

        Set<String> used = new HashSet<>();

//        for (int i = 0; i < SIZE; i++) {
//            gameChallengeMapper.findChallengeIds();
//
//            UserChallengeDummyDto dto = UserChallengeDummyDto.builder()
//                    .libraryId(libraryId)            // 유저가 가지고 있는 libraryId
//                    .challengeId(challengeId)        // 해당 게임의 challenge ID
//                    .challengedAt()  // 달성 시간
//                    .build();
//
//            gameChallengeMapper.saveUserChallenge(dto);
//        }
    }
}
