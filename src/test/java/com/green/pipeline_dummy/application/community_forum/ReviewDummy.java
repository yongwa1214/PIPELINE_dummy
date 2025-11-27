package com.green.pipeline_dummy.application.community_forum;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community_review.ReviewMapper;
import com.green.pipeline_dummy.application.community_review.model.ReviewCommentDummyDto;
import com.green.pipeline_dummy.application.community_review.model.ReviewDummyDto;
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
public class ReviewDummy extends MbDummy {
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ForumMapper forumMapper;

    Faker faker = new Faker(Locale.KOREA);

    // 리뷰 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveReview() {
        final int SIZE = 1_000_000;

        List<Long> releasedGameIds = forumMapper.findGameIds();
        List<Long> gamerIds = forumMapper.findGamerIds();
        Set<String> used = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            // 게임
            Long gmId = gamerIds.get(faker.number().numberBetween(0, gamerIds.size()));
            Long gameId = releasedGameIds.get(faker.number().numberBetween(0, releasedGameIds.size()));

            String key = gmId + "_" + gameId;
            if (used.contains(key)) continue;
            used.add(key);

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

            ReviewDummyDto reviewDummyDto = ReviewDummyDto.builder()
                    .gmProfileId(gmId)
                    .gameId(gameId)
                    .reviewContents(faker.lorem().paragraph())
                    .reviewLikeCount(faker.number().numberBetween(0, 5000))
                    .recommend(faker.random().nextBoolean() ? 1 : 0)
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .build();

            reviewMapper.saveReview(reviewDummyDto);
        }
    }

    // 리뷰 댓글 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveReviewComment() {
        final int SIZE = 3_000_000;

        List<Long> reviewIds = reviewMapper.findReview();
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
            LocalDateTime createdAt = CommonMethod.randomDateFuture(create);
            LocalDateTime updatedAt =CommonMethod.randomDateFuture(update);

            ReviewCommentDummyDto dto = ReviewCommentDummyDto
                    .builder()
                    .userId(userIds.get(faker.number().numberBetween(0, userIds.size())))
                    .reviewId(reviewIds.get(faker.number().numberBetween(0, reviewIds.size())))
                    .reviewCommentContents(faker.lorem().paragraph())
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .build();

            reviewMapper.saveReviewComment(dto);

        }
    }
}
