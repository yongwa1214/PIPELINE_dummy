package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.entitiy.common.CommonCode;
import com.green.pipeline_dummy.entitiy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserRole(CommonCode userRole);
}
