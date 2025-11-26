package com.green.pipeline_dummy.application.friend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class FriendReq {
    Long sender;
    Long receiver;
    String frStatus;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
