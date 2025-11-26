package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.entity.game.mapping.GameTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameTagRepository extends JpaRepository<GameTag, Long> {
}
