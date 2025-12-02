package com.green.pipeline_dummy.application.game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class PriceDto {
    String countryCode;
    List<Long> gameList;
}
