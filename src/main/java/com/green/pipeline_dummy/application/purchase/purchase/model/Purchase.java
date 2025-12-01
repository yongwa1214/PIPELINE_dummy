package com.green.pipeline_dummy.application.purchase.purchase.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Purchase {
    Long purchaseId;
    Long gm_profileId;
    Long methodId;
    Long currencyCode;
    BigDecimal price;
    BigDecimal walletUsed;
    BigDecimal paidPrice;
    LocalDateTime purchaseDate;
    String pg_tid;

}
