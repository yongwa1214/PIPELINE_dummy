package com.green.pipeline_dummy.application.purchase.purchase.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
public class Purchase {
    Long purchaseId;
    Long gmProfileId;
    Long methodId;
    String currencyCode;
    BigDecimal discountPrice;
    BigDecimal walletUsed;
    BigDecimal paidPrice;
    LocalDateTime purchaseDate;
    String pgTid;

}
