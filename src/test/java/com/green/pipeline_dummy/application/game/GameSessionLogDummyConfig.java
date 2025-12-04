package com.green.pipeline_dummy.application.game;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class GameSessionLogDummyConfig {

    @Bean
    public GameSessionLogDummy gameSessionLogDummy() {
        return new GameSessionLogDummy();
    }
}
