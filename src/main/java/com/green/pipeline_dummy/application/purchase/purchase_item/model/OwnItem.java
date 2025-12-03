package com.green.pipeline_dummy.application.purchase.purchase_item.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OwnItem {
    Long gmProfileId;
    LocalDateTime updatedAt;
    Long itemId;
}
