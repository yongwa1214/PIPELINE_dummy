package com.green.pipeline_dummy.application.game.game_challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class UserChallengeDummyDto {
    private Long libraryId;
    private Long challengeId;
    private LocalDateTime challengedAt;
}
