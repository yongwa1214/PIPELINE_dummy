package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.entity.game.mapping.GamePlaymode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlaymodeRepository extends JpaRepository<GamePlaymode, Long> {
}
