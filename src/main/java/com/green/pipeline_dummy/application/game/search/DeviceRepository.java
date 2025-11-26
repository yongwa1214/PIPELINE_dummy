package com.green.pipeline_dummy.application.game.search;

import com.green.pipeline_dummy.entity.game.searchtag.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
