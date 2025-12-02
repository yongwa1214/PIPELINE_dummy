package com.green.pipeline_dummy.application.game;

import com.green.pipeline_dummy.application.cart.model.WishListReq;
import com.green.pipeline_dummy.application.game.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {
    List<GameIdRes> findByStatus();
    List<Long> findGameIds();
    List<Long> findAllGameIds();
    List<PriceIdRes> findGamePrice(PriceDto dto);
    List<DiscountRes> findDiscount(DiscountDto dto);
}
