package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.entitiy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {
}
