package com.green.pipeline_dummy.application.game.mapping;

import com.green.pipeline_dummy.entity.game.mapping.GameDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDeviceRepository extends JpaRepository<GameDevice, Long> {
}
