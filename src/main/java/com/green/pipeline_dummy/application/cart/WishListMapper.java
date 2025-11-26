package com.green.pipeline_dummy.application.cart;

import com.green.pipeline_dummy.application.cart.model.WishListReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishListMapper {
    void saveWishList(WishListReq req);
}
