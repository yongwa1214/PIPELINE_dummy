package com.green.pipeline_dummy.application.purchase;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.library.LibraryMapper;
import com.green.pipeline_dummy.application.library.model.LibraryDto;
import com.green.pipeline_dummy.application.purchase.purchase_item.PurchaseItemMapper;
import com.green.pipeline_dummy.application.purchase.purchase_item.model.OwnItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LibraryDummy extends MbDummy {
    @Autowired PurchaseItemMapper purchaseItemMapper;
    @Autowired LibraryMapper libraryMapper;

    @Test
    @Rollback(false)
    void saveLibraryFromPurchaseDetail() {

        int batchSize = 50000;  // 한 번에 가져올 row 수
        int offset = 0;

        while (true) {
            // batch 단위로 조회
            List<OwnItem> purchaseInfo = purchaseItemMapper.findSTOWN(batchSize, offset);
            if (purchaseInfo.isEmpty()) break;  // 더 이상 데이터 없으면 종료

            List<LibraryDto> libraryList = new ArrayList<>();

            for (OwnItem item : purchaseInfo) {
                LibraryDto dto = LibraryDto.builder()
                        .ownerId(item.getGmProfileId())
                        .itemId(item.getItemId())
                        .libStatus("ST-OWN")
                        .playNow(0)
                        .lastPlay(null)
                        .gotAt(item.getUpdatedAt())
                        .build();
                libraryList.add(dto);
            }

            // batch insert
            libraryMapper.addLibrary(libraryList);

            offset += batchSize;
        }
    }
}