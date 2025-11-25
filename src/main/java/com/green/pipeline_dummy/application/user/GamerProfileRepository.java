package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.entitiy.user.GamerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamerProfileRepository extends JpaRepository<GamerProfile, Long> {
}
