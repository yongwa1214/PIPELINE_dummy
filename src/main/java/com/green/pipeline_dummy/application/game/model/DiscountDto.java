package com.green.pipeline_dummy.application.game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class DiscountDto {
    LocalDateTime date;
    List<Long> gameList;
}
