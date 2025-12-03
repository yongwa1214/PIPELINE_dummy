package com.green.pipeline_dummy.application.community;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community_review.ReviewMapper;
import com.green.pipeline_dummy.application.community_review.model.ReviewCommentDummyDto;
import com.green.pipeline_dummy.application.community_review.model.ReviewDummyDto;
import com.green.pipeline_dummy.application.library.LibraryMapper;
import com.green.pipeline_dummy.model.RandomDate;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewDummy extends MbDummy {
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private LibraryMapper libraryMapper;

    Faker faker = new Faker(Locale.KOREA);

    // 리뷰 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveReview() {
        final int SIZE = 500_000;

        List<Long> gamerIds = forumMapper.findGamerIds();
        Collections.shuffle(gamerIds);

        Set<String> usedCombinations = new HashSet<>();

        // 모든 유저를 순회하면서 리뷰 생성
        for (int i = 0; i < SIZE; i++) {
            // 유저 선택 (순환 구조)
            Long gmId = gamerIds.get(i % gamerIds.size());

            // 해당 유저가 가진 라이브러리 목록
            List<Long> libraryIds = libraryMapper.findLibraryIds(gmId);
            if (libraryIds == null || libraryIds.isEmpty()) continue;

            // 라이브러리 리스트를 섞어서 중복 방지
            Collections.shuffle(libraryIds);

            for (Long libId : libraryIds) {
                String key = gmId + "_" + libId;

                // 중복이면 다른 라이브러리 선택
                if (usedCombinations.contains(key)) continue;

                usedCombinations.add(key); // 사용 기록

                // 날짜 생성
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
                        .libraryId(libId)
                        .reviewContents(faker.lorem().paragraph())
                        .reviewLikeCount(faker.number().numberBetween(0, 5000))
                        .recommend(faker.random().nextBoolean() ? 1 : 0)
                        .createdAt(createdAt)
                        .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                        .build();

                try {
                    reviewMapper.saveReview(reviewDummyDto); // INSERT
                } catch (Exception e) {
                    // 중복 또는 DB 문제 발생 시 건너뜀
                    continue;
                }

                break; // 하나만 선택 후 루프 탈출 → 한 유저가 한 루프에서 한 리뷰만 추가
            }
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
