package com.green.pipeline_dummy.application.community.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class ForumBookmarkDummyDto {
    private Long forumId;
    private Long userId;
    private LocalDateTime createdAt;
}
