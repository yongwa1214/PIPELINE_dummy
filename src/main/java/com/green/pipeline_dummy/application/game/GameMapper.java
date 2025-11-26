package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.application.cart.model.WishListReq;
import com.green.pipeline_dummy.application.game.model.GameIdRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {
    List<GameIdRes> findByStatus();
}
