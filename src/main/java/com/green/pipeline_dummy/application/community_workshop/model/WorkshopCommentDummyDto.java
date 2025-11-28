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
public class WorkshopCommentDummyDto {
    private Long userId;
    private Long workshopId;
    private String workshopCommentContents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isDeleted;
}
