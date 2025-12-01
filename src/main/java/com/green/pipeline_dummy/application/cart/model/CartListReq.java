package com.green.pipeline_dummy.application.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class CartListReq {
    Long gameId;
    Long gmProfileId;
    String purchaseType;
    LocalDateTime createdAt;
}
