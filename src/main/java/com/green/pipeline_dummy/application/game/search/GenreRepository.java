package com.green.pipeline_dummy.application.game.search;

import com.green.pipeline_dummy.entity.game.searchtag.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
