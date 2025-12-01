package com.green.pipeline_dummy.application.game.game_challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class GcInsertDto {
    private Long game_id;
    private String title;
    private String description;
}
