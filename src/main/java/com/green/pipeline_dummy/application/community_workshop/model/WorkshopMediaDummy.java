package com.green.pipeline_dummy.application.community_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class WorkshopMediaDummy {
    private Long workshopId;
    private String workshopMedia;
    private String workshopMediaType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
