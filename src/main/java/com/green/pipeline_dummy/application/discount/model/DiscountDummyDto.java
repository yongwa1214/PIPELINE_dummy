package com.green.pipeline_dummy.application.discount.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class DiscountDummyDto {
    private Long eventId;
    private Long gameId;
    private BigDecimal discountRate;
}
