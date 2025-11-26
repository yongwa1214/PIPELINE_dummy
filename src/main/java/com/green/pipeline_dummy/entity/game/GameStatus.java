package com.green.pipeline_dummy.entity.game;

import lombok.Getter;

@Getter
public enum GameStatus {
    ST_ACT("ST-ACT"),
    ST_NACT("ST-NACT"),
    ST_PEND("ST-PEND");

    private final String code;

    GameStatus(String code) {
        this.code = code;
    }
}
