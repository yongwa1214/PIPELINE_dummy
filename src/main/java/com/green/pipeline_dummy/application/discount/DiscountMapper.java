package com.green.pipeline_dummy.application.discount;

import com.green.pipeline_dummy.application.discount.model.DiscountDummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscountMapper {
    void saveDiscount(DiscountDummyDto dto);
    List<Long> findEventIds();
}
