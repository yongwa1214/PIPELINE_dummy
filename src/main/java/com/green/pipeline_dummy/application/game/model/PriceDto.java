package com.green.pipeline_dummy.application.game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PriceDto {
    String countryCode;
    List<GameIdRes> gameList;
}
