package com.green.pipeline_dummy.application.friend;

import com.green.pipeline_dummy.application.friend.model.FriendReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendMapper {
    void saveFriend(FriendReq req);
}
