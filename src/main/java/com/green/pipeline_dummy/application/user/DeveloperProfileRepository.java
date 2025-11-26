package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.entity.user.DeveloperProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperProfileRepository  extends JpaRepository<DeveloperProfile, Long> {
}
