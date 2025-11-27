package com.green.pipeline_dummy.application.regional_price;

import com.green.pipeline_dummy.application.regional_price.model.RegionalPriceDummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionalPriceMapper {
    void saveRegionalPrice(RegionalPriceDummyDto dto);
    String findPrimaryCurrency(String countryCode);
}
