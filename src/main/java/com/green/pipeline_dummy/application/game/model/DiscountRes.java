package com.green.pipeline_dummy.application.game.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
public class DiscountRes {
    Long discountId;
    BigDecimal discountRate;
}
