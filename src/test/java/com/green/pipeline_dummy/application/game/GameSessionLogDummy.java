package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.game.session.GameSessionLogMapper;
import com.green.pipeline_dummy.application.game.session.dto.GameSessionLogDto;
import com.green.pipeline_dummy.application.game.game_challenge.model.LibraryForChallengeDto;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


public class GameSessionLogDummy extends MbDummy {

    @Autowired
    private GameSessionLogMapper gameSessionLogMapper;

    private final Random random = new Random();


    public void saveGameSessionLogs() {

        List<LibraryForChallengeDto> libs = gameSessionLogMapper.getLibrariesForSession();

        int count = 0;

        for (LibraryForChallengeDto lib : libs) {

            int sessionCount = random.nextInt(8) + 3;

            for (int i = 0; i < sessionCount; i++) {

                LocalDateTime start = LocalDateTime.now()
                        .minusDays(random.nextInt(60))
                        .minusHours(random.nextInt(12))
                        .minusMinutes(random.nextInt(60));

                LocalDateTime end = start.plusMinutes(random.nextInt(120) + 10);

                GameSessionLogDto dto = GameSessionLogDto.builder()
                        .libraryId(lib.getLibraryId())
                        .startTime(start)
                        .endTime(end)
                        .build();

                count += gameSessionLogMapper.insertGameSession(dto);
            }
        }

        System.out.println("Game Session Log inserted: " + count);
    }
}
