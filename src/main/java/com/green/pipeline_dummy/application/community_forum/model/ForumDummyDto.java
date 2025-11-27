package com.green.pipeline_dummy.application.community_forum.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ForumDummyDto {
    private Long userId;
    private Long gameId;
    private String forumTitle;
    private String forumContents;
    private Integer pinned;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isDeleted;
}
