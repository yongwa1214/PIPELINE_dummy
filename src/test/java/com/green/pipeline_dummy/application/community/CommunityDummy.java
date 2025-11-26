package com.green.pipeline_dummy.application.community;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community.model.ForumBookmarkDummyDto;
import com.green.pipeline_dummy.application.community.model.ForumCommentDummyDto;
import com.green.pipeline_dummy.application.community.model.ForumDummyDto;
import com.green.pipeline_dummy.application.community.model.ForumMediaDummyDto;
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
public class CommunityDummy extends MbDummy {
    @Autowired CommunityMapper communityMapper;

    Faker faker = new Faker(Locale.KOREA);

    @Test
    @Rollback(false)
    void saveForum() {
        final int SIZE = 2_000;

        List<Long> releasedGameIds = communityMapper.findGameIds(); // '출시중' 게임만
        List<Long> userIds = communityMapper.findUserIds(); // '모든' 유저
        for (int i = 0; i < SIZE; i++) {
            // 공지 여부

            Long gameId = releasedGameIds.get(
                    faker.number().numberBetween(0, releasedGameIds.size()));
            /*
            int pinnedCnt = communityMapper.countPinnedByGameId(gameId);

            int pinned = 0;
            if (pinnedCnt < 3) {
                pinned = faker.number().numberBetween(0, 10) == 0 ? 1 : 0;
            }
            */

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

            ForumDummyDto dto = ForumDummyDto
                    .builder()
                    .userId(userIds.get(faker.number().numberBetween(0, userIds.size())))
                    .gameId(gameId)
                    .forumTitle(faker.pokemon().name())
                    .forumContents(faker.lorem().paragraph())
                    .pinned(0)
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    // 삭제 확률 10%
                    .isDeleted(faker.number().numberBetween(0, 10) == 0 ? 1 : 0)
                    .build();

            communityMapper.saveForum(dto);
        }
    }

    @Test
    @Rollback(value = false)
    void saveForumComment() {
        final int SIZE = 100_000;

        List<Long> userIds = communityMapper.findUserIds(); // '모든' 유저
        List<Long> forumIds = communityMapper.findForumIds();
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

            ForumCommentDummyDto dto = ForumCommentDummyDto
                    .builder()
                    .userId(userIds.get(faker.number().numberBetween(0, userIds.size())))
                    .forumId(forumIds.get(faker.number().numberBetween(0, forumIds.size())))
                    .forumCommentContents( faker.lorem().paragraph() + " " +
                            faker.name().firstName() + " " +
                            faker.number().numberBetween(1, 9999))
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .isDeleted(faker.number().numberBetween(0, 10) == 0 ? 1 : 0)
                    .build();

            communityMapper.saveForumComment(dto);
        }
    }

    @Test
    @Rollback(value = false)
    void saveForumMedia() {
        final int SIZE = 100_000;

        List<Long> forumIds = communityMapper.findForumIds();
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

            ForumMediaDummyDto dto = ForumMediaDummyDto.builder()
                    .forumId(forumIds.get(faker.number().numberBetween(0, forumIds.size())))
                    .forumMedia(faker.file().fileName()) // 임의 경로
                    .forumMediaType(faker.options().option("video","image","gif"))
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .build();

            communityMapper.saveForumMedia(dto);
        }
    }

    @Test
    @Rollback(value = false)
    void saveForumBookmark() {
        final int SIZE = 100_000;

        List<Long> userIds = communityMapper.findUserIds(); // '모든' 유저
        List<Long> forumIds = communityMapper.findForumIds();
        for (int i = 0; i < SIZE; i++) {
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
            LocalDateTime updatedAt =CommonMethod.randomDateFuture(update);
            LocalDateTime createdAt = CommonMethod.randomDateFuture(create);

            ForumBookmarkDummyDto dto = ForumBookmarkDummyDto
                    .builder()
                    .build();
        }

    }
}
