package com.green.pipeline_dummy.application.community_news.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class NewsMediaDummyDto {
    private Long newsId;
    private String newsMedia;
    private String newsMediaType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
