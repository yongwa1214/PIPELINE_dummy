package com.green.pipeline_dummy.application.community.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ForumCommentDummyDto {
    private Long userId;
    private Long forumId;
    private String forumCommentContents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isDeleted;
}
