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
public class ReviewCommentDummyDto {
    private Long userId;
    private Long reviewId;
    private String reviewCommentContents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
