package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.application.user.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Long> findDeveloperIds();
    UserInfo findCountryAndCurrency(Long userId);
}
