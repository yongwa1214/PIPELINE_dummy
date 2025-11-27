package com.green.pipeline_dummy.application.community_forum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ForumMediaDummyDto {
    private Long forumId;
    private String forumMedia;
    private String forumMediaType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
