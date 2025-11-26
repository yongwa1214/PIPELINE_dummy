package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.entity.game.PlayShot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayShotRepository extends JpaRepository<PlayShot, Long> {
}
