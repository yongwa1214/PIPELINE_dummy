package com.green.pipeline_dummy.application.purchase.purchase;

import com.green.pipeline_dummy.application.purchase.purchase.model.Purchase;
import com.green.pipeline_dummy.application.purchase.purchase.model.PurchaseMethod;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    List<PurchaseMethod> findPurchaseMethod(String countryCode);
    void savePurchase(Purchase dto);
}
