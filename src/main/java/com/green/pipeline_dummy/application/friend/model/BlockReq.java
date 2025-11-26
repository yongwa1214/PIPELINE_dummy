package com.green.pipeline_dummy.application.friend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BlockReq {
    Long fromUser;
    Long toUser;

}
