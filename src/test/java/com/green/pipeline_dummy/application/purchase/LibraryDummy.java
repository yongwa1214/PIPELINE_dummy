package com.green.pipeline_dummy.application.purchase;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.library.LibraryMapper;
import com.green.pipeline_dummy.application.library.model.LibraryDto;
import com.green.pipeline_dummy.application.purchase.purchase_item.PurchaseItemMapper;
import com.green.pipeline_dummy.application.purchase.purchase_item.model.OwnItem;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LibraryDummy extends MbDummy {
    @Autowired PurchaseItemMapper purchaseItemMapper;
    @Autowired LibraryMapper libraryMapper;

    void saveLibraryFromPurchaseDetail(){
        List<OwnItem> purchaseInfo = purchaseItemMapper.findSTOWN();
        List<LibraryDto> lbiraryList = new ArrayList<>();
    }
}
