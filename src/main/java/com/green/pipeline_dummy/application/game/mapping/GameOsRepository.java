package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.entity.game.mapping.GameOs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameOsRepository extends JpaRepository<GameOs, Long> {
}
