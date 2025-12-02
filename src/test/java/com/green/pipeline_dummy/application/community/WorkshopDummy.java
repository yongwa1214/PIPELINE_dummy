package com.green.pipeline_dummy.application.community;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.community.model.ForumBookmarkDummyDto;
import com.green.pipeline_dummy.application.community_news.model.NewsMediaDummyDto;
import com.green.pipeline_dummy.application.community_workshop.WorkshopMapper;
import com.green.pipeline_dummy.application.community_workshop.model.*;
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
public class WorkshopDummy extends MbDummy {
    @Autowired
    private WorkshopMapper workshopMapper;
    @Autowired
    private ForumMapper forumMapper;

    Faker faker = new Faker(Locale.KOREA);

    // 창작마당 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveWorkshop() {
        final int SIZE = 1_000_000;

        List<Long> releasedGameIds = forumMapper.findGameIds();
        List<Long> gamerIds = forumMapper.findGamerIds();
        for (int i = 0; i < SIZE; i++) {
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

            // DTO 생성
            WorkshopDummyDto dto = WorkshopDummyDto.builder()
                    .gmProfileId(gamerIds.get(faker.number().numberBetween(0, gamerIds.size())))
                    .gameId(gameId)
                    .workshopThumbnail(faker.internet().image())
                    .workshopTitle(faker.book().title())
                    .workshopContents(faker.lorem().paragraph())
                    .categoryTag(faker.options().option("mode", "skin"))
                    .workshopLikeCount(faker.number().numberBetween(0, 8000))
                    .rating(faker.number().randomDouble(1, 0, 5))
                    .filePath("/uploads/workshop/" + faker.file().fileName())
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .isDeleted(faker.number().numberBetween(0, 10) == 0 ? 1 : 0)
                    .build();

            workshopMapper.saveWorkshop(dto);
        }
    }

    // 창작마당 이미지 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveWorkshopMedia() {
        final int SIZE = 2_000_000;

        List<Long> workshopIds = workshopMapper.findWorkshopIds();
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

            WorkshopMediaDummy dto = WorkshopMediaDummy.builder()
                    .workshopId(workshopIds.get(faker.number().numberBetween(0, workshopIds.size())))
                    .workshopMedia(mediaUrl)
                    .workshopMediaType(faker.options().option("video","image","gif"))
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .build();

            workshopMapper.saveWorkshopMedia(dto);
        }
    }

    // 창작마당 댓글 더미 데이터 넣기
    @Test
    @Rollback(false)
    void saveWorkshopComment() {
        final int SIZE = 2_000_000;

        List<Long> workshopIds = workshopMapper.findWorkshopIds();
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

            WorkshopCommentDummyDto dto = WorkshopCommentDummyDto.builder()
                    .userId(userIds.get(faker.number().numberBetween(0, userIds.size())))
                    .workshopId(workshopIds.get(faker.number().numberBetween(0, workshopIds.size())))
                    .workshopCommentContents(faker.lorem().paragraph())
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .isDeleted(faker.number().numberBetween(0, 10) == 0 ? 1 : 0)
                    .build();

            workshopMapper.saveWorkshopComment(dto);
        }
    }

    // 포럼 글 북마크 더미 데이터
    @Test
    @Rollback(value = false)
    void saveWorkshopBookmark() {
        final int SIZE = 2_000_000;
        List<Long> userIds = forumMapper.findUserIds();
        List<Long> workshopIds = workshopMapper.findWorkshopIds();
        Set<String> existing = new HashSet<>();

        for (int i = 0; i < SIZE; i++) {
            Long userId = userIds.get(faker.number().numberBetween(0, userIds.size()));
            Long workshopId = workshopIds.get(faker.number().numberBetween(0, workshopIds.size()));
            String key = userId + "-" + workshopId;

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

            WorkshopBookmarkDummyDto dto = WorkshopBookmarkDummyDto.builder()
                    .userId(userId)
                    .workshopId(workshopId)
                    .createdAt(createdAt)
                    .build();

            workshopMapper.saveWorkshopBookmark(dto);
        }
    }

    // 포럼 글 팔로우 더미 데이터
    @Test
    @Rollback(value = false)
    void saveWorkshopFollow() {
        final int SIZE = 2_000_000;
        List<Long> gamerIds = forumMapper.findGamerIds();
        List<Long> workshopIds = workshopMapper.findWorkshopIds();
        Set<String> existing = new HashSet<>();

        for (int i = 0; i < SIZE; i++) {
            Long gamerId = gamerIds.get(faker.number().numberBetween(0, gamerIds.size()));
            Long workshopId = workshopIds.get(faker.number().numberBetween(0, workshopIds.size()));
            String key = gamerId + "-" + workshopId;

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

            WorkshopFollowDummyDto dto = WorkshopFollowDummyDto.builder()
                    .gmProfileId(gamerId)
                    .workshopId(workshopId)
                    .createdAt(createdAt)
                    .build();

            workshopMapper.saveWorkshopFollow(dto);
        }
    }
}
