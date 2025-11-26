package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.entity.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
