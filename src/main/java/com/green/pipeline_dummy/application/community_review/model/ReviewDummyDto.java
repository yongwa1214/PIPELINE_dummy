package com.green.pipeline_dummy.application.community_review.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ReviewDummyDto {
    private Long gmProfileId;
    private Long libraryId;
    private String reviewContents;
    private int reviewLikeCount;
    private int recommend;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
