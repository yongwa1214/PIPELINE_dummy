package com.green.pipeline_dummy.application.purchase.purchase_item.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PurchaseItemFinal {
    Long purchaseId;
    List<PurchaseItemDto> purchaseItem;
    LocalDateTime updatedAt;
}
