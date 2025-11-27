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
public class NewsDummyDto {
    private Long dpProfileId;
    private Long gameId;
    private String newsTitle;
    private String newsContents;
    private String newsPatchVersion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isDeleted;
}
