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
public class WorkshopFollowDummyDto {
    private Long workshopId;
    private Long userId;
    private LocalDateTime createdAt;
}
