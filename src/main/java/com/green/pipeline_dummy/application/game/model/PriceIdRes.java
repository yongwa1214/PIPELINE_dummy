package com.green.pipeline_dummy.application.game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class PriceIdRes {
    BigDecimal price_amount;
}
