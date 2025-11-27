package com.green.pipeline_dummy.application.regional_price.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class RegionalPriceDummyDto {
    private String countryCode;
    private String currencyCode;
    private Long gameId;
    private BigDecimal priceAmount;
    private LocalDateTime createdAt;
}
