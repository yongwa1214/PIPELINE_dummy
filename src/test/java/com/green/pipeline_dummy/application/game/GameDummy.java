package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.JpaDummy;
import com.green.pipeline_dummy.application.common.CommonCodeRepository;
import com.green.pipeline_dummy.application.game.mapping.GameMappingDummy;
import com.green.pipeline_dummy.application.user.DeveloperProfileRepository;
import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.GameStatus;
import com.green.pipeline_dummy.entity.user.DeveloperProfile;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
@Import(GameSessionLogDummyConfig.class)  // ⭐ test 빈 가져오기
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameDummy extends JpaDummy {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    DeveloperProfileRepository developerProfileRepository;

    @Autowired
    CommonCodeRepository commonCodeRepository;

    @Autowired
    EntityManager em;

    // 부모 게임 리스트 (자식 게임의 FK 연결용)
    private List<Game> parentGameList;


    // ================================
    // 🔥 전체 게임 더미 생성 실행
    // ================================
    @Test
    @Rollback(false)
    void saveGames() {

        // ★ 부모(시리즈 루트) 게임 먼저 생성
        parentGameList = createParentGames(30_000);

        // ★ FK로 부모 연결된 자식 게임 생성
        createChildGames(900);
    }


    // ================================
    // 부모 게임 생성 (baseGame = null)
    // ================================
    private List<Game> createParentGames(int count) {

        List<DeveloperProfile> devList = developerProfileRepository.findAll();

        for (int i = 0; i < count; i++) {

            DeveloperProfile dp = devList.get(
                    ThreadLocalRandom.current().nextInt(devList.size())
            );

            Game game = Game.builder()
                    .baseGame(null)   // 부모 게임은 baseGame 없음
                    .developerProfile(dp)
                    .gameName(faker.book().title())
                    .releaseDate(randomDate())
                    .publisher(dp.getDeveloperName())
                    .description(faker.lorem().paragraph())
                    .thumbnail("https://dummyimage.com/600x400/" +
                            faker.color().hex().substring(1) + "/ffffff")
                    .status(getRandomStatus())
                    .totalDownload(ThreadLocalRandom.current().nextInt(0, 500000))
                    .build();

            gameRepository.save(game);

            if (i % 500 == 0) {
                gameRepository.flush();
                em.clear();
            }
        }

        gameRepository.flush();
        em.clear();
        return gameRepository.findAll();
    }


    // ================================
    // 자식 게임 생성 (baseGame = 부모 게임)
    // ================================
    private void createChildGames(int count) {

        List<DeveloperProfile> devList = developerProfileRepository.findAll();
        int parentSize = parentGameList.size();

        for (int i = 0; i < count; i++) {

            DeveloperProfile dp = devList.get(
                    ThreadLocalRandom.current().nextInt(devList.size())
            );

            Game parent = parentGameList.get(
                    ThreadLocalRandom.current().nextInt(parentSize)
            );

            Game game = Game.builder()
                    .baseGame(parent)          // Self FK
                    .developerProfile(dp)
                    .gameName(faker.book().title() + " " + faker.number().digits(2))
                    .releaseDate(randomDate())
                    .publisher(dp.getDeveloperName())
                    .description(faker.lorem().paragraph())
                    .thumbnail("https://dummyimage.com/600x400/" +
                            faker.color().hex().substring(1) + "/ffffff")
                    .status(getRandomStatus())
                    .totalDownload(ThreadLocalRandom.current().nextInt(0, 500000))
                    .build();

            gameRepository.save(game);

            if (i % 1000 == 0) {
                gameRepository.flush();
                em.clear();
            }
        }

        gameRepository.flush();
        em.clear();
    }


    // ================================
    // 랜덤 ENUM 생성
    // ================================
    private GameStatus getRandomStatus() {
        GameStatus[] arr = GameStatus.values();
        return arr[ThreadLocalRandom.current().nextInt(arr.length)];
    }

    // ================================
    // 랜덤 날짜
    // ================================
    private LocalDate randomDate() {
        long start = LocalDate.of(2020, 1, 1).toEpochDay();
        long end = LocalDate.of(2025, 12, 31).toEpochDay();
        return LocalDate.ofEpochDay(
                ThreadLocalRandom.current().nextLong(start, end)
        );
    }


    // ================================
    // 🔥 매핑 더미 실행
    // ================================
    @Autowired
    private GameMappingDummy gameMappingDummy;

    @Test
    @Rollback(false)
    void saveMappings() {
        gameMappingDummy.generateMappings();
    }
    @Autowired
    private PlayShotDummy playShotDummy;

    @Test
    @Rollback(false)
    void savePlayShots() {
        playShotDummy.generatePlayShots();
    }
    @Autowired
    private RegionalPriceDummy regionalPriceDummy;

    @Test
    @Rollback(false)
    void saveRegionalPrices() {
        regionalPriceDummy.generateRegionalPrices();
    }

    @Autowired
    private GameChallengeDummy gameChallengeDummy;

    @Test
    @Rollback(false)
    void saveGameChallenges() {
        gameChallengeDummy.generateChallenges();
    }

    @Autowired
    private GameSessionLogDummy gameSessionLogDummy;

    @Test
    @Rollback(false)
    void saveGameSessionLogs() {
        gameSessionLogDummy.saveGameSessionLogs();
    }
}
