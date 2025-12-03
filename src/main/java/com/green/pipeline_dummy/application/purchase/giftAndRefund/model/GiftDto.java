package com.green.pipeline_dummy.application.purchase.giftAndRefund.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GiftDto {
    Long itemId;
    Long senderId;
    Long receiverId;
    String message;
    LocalDateTime sentAt;
    LocalDateTime receivedAt;
    String giftStatus;
}
