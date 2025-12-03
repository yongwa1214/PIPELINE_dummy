package com.green.pipeline_dummy.application.purchase.purchase_item;

import com.green.pipeline_dummy.application.purchase.purchase_item.model.OwnItem;
import com.green.pipeline_dummy.application.purchase.purchase_item.model.PurchaseItemFinal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseItemMapper {
    void saveDetail(PurchaseItemFinal dto);
    List<OwnItem> findSTOWN(
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}
