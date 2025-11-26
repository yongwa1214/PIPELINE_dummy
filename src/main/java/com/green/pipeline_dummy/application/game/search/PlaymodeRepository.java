package com.green.pipeline_dummy.application.game.search;

import com.green.pipeline_dummy.entity.game.searchtag.Playmode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaymodeRepository extends JpaRepository<Playmode, Long> {
}
