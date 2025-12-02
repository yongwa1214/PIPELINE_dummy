package com.green.pipeline_dummy.application.game.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class DiscountRes {
    Long discountId;
    BigDecimal discountRate;
}
