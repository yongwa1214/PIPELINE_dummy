package com.green.pipeline_dummy.application.community_forum;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community_forum.model.ForumBookmarkDummyDto;
import com.green.pipeline_dummy.application.community_forum.model.ForumCommentDummyDto;
import com.green.pipeline_dummy.application.community_forum.model.ForumDummyDto;
import com.green.pipeline_dummy.application.community_forum.model.ForumMediaDummyDto;
import com.green.pipeline_dummy.model.RandomDate;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommunityDummy extends MbDummy {
    @Autowired
    private ForumMapper communityMapper;

    Faker faker = new Faker(Locale.KOREA);

    // 포럼 글 더미 데이터
    @Test
    @Rollback(false)
    void saveForum() {
        final int SIZE = 1_000_000;

        List<Long> releasedGameIds = communityMapper.findGameIds(); // '출시중' 게임만
        List<Long> userIds = communityMapper.findUserIds(); // '모든' 유저
        for (int i = 0; i < SIZE; i++) {
            // 공지 여부

            Long gameId = releasedGameIds.get(
                    faker.number().numberBetween(0, releasedGameIds.size()));

            int pinnedCnt = communityMapper.countPinnedByGameId(gameId);

            int pinned = 0;
            if (pinnedCnt < 3) {
                pinned = faker.number().numberBetween(0, 10) == 0 ? 1 : 0;
            }


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
                    .pinned(pinned)
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    // 삭제 확률 10%
                    .isDeleted(faker.number().numberBetween(0, 10) == 0 ? 1 : 0)
                    .build();

            communityMapper.saveForum(dto);
        }
    }

    // 포럼 글 댓글 더미 데이터
    @Test
    @Rollback(value = false)
    void saveForumComment() {
        final int SIZE = 3_000_000;

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

    // 포럼 글 이미지 넣기
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

    // 포럼 글 북마크 더미 데이터
    @Test
    @Rollback(value = false)
    void saveForumBookmark() {
        final int SIZE = 100_000;

        List<Long> userIds = communityMapper.findUserIds(); // 모든 유저
        List<Long> forumIds = communityMapper.findForumIds(); // 모든 포럼
        Set<String> existing = new HashSet<>();

        for (int i = 0; i < SIZE; i++) {
            Long userId = userIds.get(faker.number().numberBetween(0, userIds.size()));
            Long forumId = forumIds.get(faker.number().numberBetween(0, forumIds.size()));
            String key = userId + "-" + forumId;

            if (existing.contains(key)) continue; // 중복 방지
            existing.add(key);

            RandomDate create = RandomDate.builder()
                    .startYear(2023)
                    .startMonth(3)
                    .startDate(1)
                    .endMonth(11)
                    .endDate(20)
                    .build();
            LocalDateTime createdAt = CommonMethod.randomDateFuture(create);

            ForumBookmarkDummyDto dto = ForumBookmarkDummyDto.builder()
                    .userId(userId)
                    .forumId(forumId)
                    .createdAt(createdAt)
                    .build();

            communityMapper.saveForumBookMark(dto);
        }

    }
}
