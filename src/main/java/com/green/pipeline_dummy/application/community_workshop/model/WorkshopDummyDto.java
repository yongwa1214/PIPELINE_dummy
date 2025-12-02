package com.green.pipeline_dummy.application.community_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class WorkshopDummyDto {
    private Long gmProfileId;
    private Long gameId;
    private String workshopThumbnail;
    private String workshopTitle;
    private String workshopContents;
    private String categoryTag;        // ENUM: 'mode', 'skin'
    private Integer workshopLikeCount;
    private Double rating;
    private String filePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isDeleted;
}
