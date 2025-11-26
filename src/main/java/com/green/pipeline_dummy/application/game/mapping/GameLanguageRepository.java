package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.entity.game.mapping.GameLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLanguageRepository extends JpaRepository<GameLanguage, Long> {
}
