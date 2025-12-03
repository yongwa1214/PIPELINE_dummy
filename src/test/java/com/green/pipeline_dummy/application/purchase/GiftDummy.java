package com.green.pipeline_dummy.application.purchase;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.purchase.giftAndRefund.GiftMapper;
import com.green.pipeline_dummy.application.purchase.giftAndRefund.model.GiftDto;
import com.green.pipeline_dummy.application.purchase.purchase_item.PurchaseItemMapper;
import com.green.pipeline_dummy.application.purchase.purchase_item.model.OwnItem;
import com.green.pipeline_dummy.model.RandomDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GiftDummy extends MbDummy {
    @Autowired GiftMapper giftMapper;
    @Autowired PurchaseItemMapper purchaseItemMapper;

    @Test
    @Rollback(false)
    void saveGift(){
        // 선물목록
        List<OwnItem> ownItem = purchaseItemMapper.findGift();
        List<GiftDto> dto = new ArrayList<>();
        for (OwnItem item : ownItem) {
            long gm_id;
            int checkDuplicate;

            do {
                // 랜덤으로 새로운 수신자 선택
                gm_id = 760_836L + (long)(Math.random() * 1_000_000);

                // 해당 유저가 이미 이 게임을 가지고 있는지 확인
                checkDuplicate = purchaseItemMapper.findOwnGame(gm_id, item.getGameId());

            } while (checkDuplicate >= 1); // 이미 가지고 있으면 다시 랜덤
            LocalDateTime receivedAt = item.getUpdatedAt().plusHours(1);
            String[] status ={"ST-PEND", "ST-ACPT", "ST-REJ"};
            String giftStatus = status[(int)(Math.random() *3)];

            // 여기까지 오면 gm_id는 중복 없이 선물 가능한 유저
            GiftDto result = GiftDto.builder()
                    .itemId(item.getItemId())
                    .senderId(item.getGmProfileId())
                    .receiverId(gm_id)
                    .message(faker.color().hex())
                    .sentAt(item.getUpdatedAt())
                    .receivedAt(giftStatus.equals("ST-PEND") ? null : receivedAt)
                    .giftStatus(giftStatus)
                    .build();
            dto.add(result);
        }
        giftMapper.saveGift(dto);
    }
}