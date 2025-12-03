package com.green.pipeline_dummy.application.game.session.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GameSessionLogDto {
    private Long playLogId;
    private Long libraryId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
