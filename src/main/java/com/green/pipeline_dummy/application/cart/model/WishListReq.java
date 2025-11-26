package com.green.pipeline_dummy.application.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class WishListReq {
    Long gameId;
    Long gmProfileId;
    LocalDateTime createdAt;
}
