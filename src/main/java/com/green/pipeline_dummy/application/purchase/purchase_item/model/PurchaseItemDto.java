package com.green.pipeline_dummy.application.purchase.purchase_item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PurchaseItemDto {
    Long purchaseUId;
    Long gameId;
    Long discountId;
    BigDecimal basePrice;
    BigDecimal finalPrice;
    String purchaseType;
    String itemStatus;
    LocalDateTime createdAt;
}
