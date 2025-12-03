package com.green.pipeline_dummy.application.game.game_challenge.model;

import lombok.Data;

@Data
public class LibraryForChallengeDto {
    private Long libraryId;   // 라이브러리 PK
    private Long itemId;      // 어떤 게임인지 확인하려고 필요
    private Long ownerId;     // 유저 ID (도전과제/로그 생성시 유용)
}
