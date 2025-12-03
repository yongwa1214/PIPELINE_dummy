package com.green.pipeline_dummy.application.purchase.giftAndRefund;

import com.green.pipeline_dummy.application.purchase.giftAndRefund.model.GiftDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GiftMapper {
    void saveGift(List<GiftDto> res);
}
