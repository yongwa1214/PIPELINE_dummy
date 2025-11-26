package com.green.pipeline_dummy.application.game;


import com.green.pipeline_dummy.application.game.GameRepository;
import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.PlayShot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static com.green.pipeline_dummy.JpaDummy.faker;

@Component
@RequiredArgsConstructor
public class PlayShotDummy {

    private final PlayShotRepository playShotRepository;
    private final GameRepository gameRepository;

    private final Random random = new Random();

    @Transactional
    public void generatePlayShots() {

        List<Game> games = gameRepository.findAll();
        System.out.println("총 게임 수 = " + games.size());

        for (Game game : games) {

            int count = 2 + random.nextInt(9); // 2~10개
            for (int i = 0; i < count; i++) {

                String seed = faker.random().hex(); // 랜덤 seed
                boolean isVideo = random.nextBoolean();

                String url;

                if (isVideo) {
                    // 영상 URL (임의로 Vimeo 링크)
                    url = "https://dummy-videos.com/video/" + seed;
                } else {
                    // 이미지 URL (picsum)
                    url = "https://picsum.photos/seed/" + seed + "/800/450";
                }

                PlayShot shot = PlayShot.builder()
                        .game(game)
                        .media(url)
                        .mediaType(isVideo ? PlayShot.MediaType.video : PlayShot.MediaType.image)
                        .build();

                playShotRepository.save(shot);
            }
        }

        System.out.println("PlayShot 더미 생성 완료!");
    }
}

