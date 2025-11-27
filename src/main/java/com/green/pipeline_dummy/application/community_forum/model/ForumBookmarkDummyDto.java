package com.green.pipeline_dummy.application.community_forum.model;

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
    private Long forumId;     // forum_id
    private Long userId;      // user_id
    private LocalDateTime createdAt;  // created_at
}
