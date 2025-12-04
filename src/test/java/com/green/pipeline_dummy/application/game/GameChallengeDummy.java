package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.entity.game.GameChallenge;
import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.application.game.GameRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class GameChallengeDummy {

    private final GameRepository gameRepository;
    private final GameChallengeRepository gameChallengeRepository;
    private final Random random = new Random();
    private final Faker faker = new Faker();
    private final EntityManager em;

    @Transactional
    public void generateChallenges() {

        List<Game> games = gameRepository.findAll();
        int batchSize = 1000;
        int counter = 0;

        for (Game game : games) {

            int challengeCount = random.nextInt(8) + 3;

            for (int i = 0; i < challengeCount; i++) {

                GameChallenge challenge = GameChallenge.builder()
                        .gameId(game.getGameId())
                        .title(faker.esports().event())
                        .description(faker.lorem().sentence(10))
                        .clearUserCount(random.nextInt(50000))
                        .hidden(random.nextInt(2))
                        .challengePic("https://picsum.photos/seed/" + faker.random().nextInt(1000000) + "/500/500")
                        .build();

                gameChallengeRepository.save(challenge);

                counter++;

                if (counter % batchSize == 0) {
                    gameChallengeRepository.flush();
                    em.clear(); // 🔥 핵심
                }
            }
        }

        gameChallengeRepository.flush();
        em.clear();
    }



}
