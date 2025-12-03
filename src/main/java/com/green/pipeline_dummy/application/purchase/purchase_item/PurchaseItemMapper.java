package com.green.pipeline_dummy.application.purchase.purchase_item;

import com.green.pipeline_dummy.application.purchase.purchase_item.model.PurchaseItemFinal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseItemMapper {
    void saveDetail(PurchaseItemFinal dto);
}
