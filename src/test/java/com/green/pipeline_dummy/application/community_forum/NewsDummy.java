package com.green.pipeline_dummy.application.community_forum;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community_news.NewsMapper;
import com.green.pipeline_dummy.application.community_news.model.NewsDummyDto;
import com.green.pipeline_dummy.application.user.UserMapper;
import com.green.pipeline_dummy.model.RandomDate;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewsDummy extends MbDummy {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private UserMapper userMapper;

    Faker faker = new Faker(Locale.KOREA);

    // 뉴스 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveNews() {
        final int SIZE = 1_000_000;

        List<Long> releasedGameIds = forumMapper.findGameIds();
        List<Long> developerIds = userMapper.findDeveloperIds();
        for (int i = 0; i < SIZE; i++) {
            // 랜덤 버전 (0~3).(0~9).(0~19)
            String patchVersion =
                    faker.number().numberBetween(0, 4) + "." +
                            faker.number().numberBetween(0, 10) + "." +
                            faker.number().numberBetween(0, 20);

            // 게임 아이디
            Long gameId = releasedGameIds.get(faker.number().numberBetween(0, releasedGameIds.size()));

            // 날짜
            RandomDate create = RandomDate.builder()
                    .startYear(2023)
                    .startMonth(3)
                    .startDate(1)
                    .endMonth(11)
                    .endDate(20)
                    .build();
            RandomDate update = RandomDate.builder()
                    .startYear(2023)
                    .startMonth(10)
                    .startDate(1)
                    .endMonth(11)
                    .endDate(20)
                    .build();
            LocalDateTime updatedAt = CommonMethod.randomDateFuture(update);
            LocalDateTime createdAt = CommonMethod.randomDateFuture(create);

            NewsDummyDto dto = NewsDummyDto.builder()
                    .dpProfileId(developerIds.get(faker.number().numberBetween(0, developerIds.size())))
                    .gameId(gameId)
                    .newsTitle(faker.pokemon().name())
                    .newsContents(faker.lorem().paragraph())
                    .newsPatchVersion(patchVersion)
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .isDeleted(faker.number().numberBetween(0, 10) == 0 ? 1 : 0)
                    .build();

            newsMapper.saveNews(dto);
        }

        // 뉴스 이미지 더미 데이터 넣기

    }

}
