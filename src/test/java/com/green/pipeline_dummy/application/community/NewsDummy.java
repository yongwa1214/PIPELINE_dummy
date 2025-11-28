package com.green.pipeline_dummy.application.community;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community_news.NewsMapper;
import com.green.pipeline_dummy.application.community_news.model.NewsCommentDummyDto;
import com.green.pipeline_dummy.application.community_news.model.NewsDummyDto;
import com.green.pipeline_dummy.application.community_news.model.NewsMediaDummyDto;
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
    }

    // 뉴스 이미지 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveNewsMedia() {
        final int SIZE = 2_000_000;

        List<Long> newsIds = newsMapper.findNewsIds();
        for (int i = 0; i < SIZE; i++) {
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

            // 랜덤 이미지 URL
            String mediaUrl = faker.internet().image();

            NewsMediaDummyDto dto = NewsMediaDummyDto.builder()
                    .newId(newsIds.get(faker.number().numberBetween(0, newsIds.size())))
                    .newsMedia(mediaUrl)
                    .newsMediaType(faker.options().option("video","image","gif"))
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .build();

            newsMapper.saveNewsMedia(dto);
        }
    }

    // 뉴스 댓글 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveNewsComment() {
        final int SIZE = 3_000_000;

        List<Long> newsIds = newsMapper.findNewsIds();
        List<Long> userIds = forumMapper.findUserIds();
        for (int i = 0; i < SIZE; i++) {
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

            NewsCommentDummyDto dto = NewsCommentDummyDto.builder()
                    .newsId(newsIds.get(faker.number().numberBetween(0, newsIds.size())))
                    .userId(userIds.get(faker.number().numberBetween(0, userIds.size())))
                    .newsCommentContents(faker.lorem().paragraph())
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .isDeleted(faker.number().numberBetween(0, 10) == 0 ? 1 : 0)
                    .build();

            newsMapper.saveNewsComment(dto);
        }
    }

}
