package com.green.pipeline_dummy.application.game.search;

import com.green.pipeline_dummy.entity.game.searchtag.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Long> {
}
