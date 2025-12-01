package com.green.pipeline_dummy.application.purchase;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.game.GameMapper;
import com.green.pipeline_dummy.application.game.model.GameIdRes;
import com.green.pipeline_dummy.application.purchase.purchase.PurchaseMapper;
import com.green.pipeline_dummy.application.user.UserMapper;
import com.green.pipeline_dummy.application.user.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseDummy extends MbDummy {
    @Autowired UserMapper userMapper;
    @Autowired GameMapper gameMapper;
    @Autowired PurchaseMapper purchaseMapper;

    @Test
    @Rollback(value = false)
    void savePurchaseProcessUntilProcessDetail(){
        final int SIZE = 100_000;
        List<GameIdRes> gameList = gameMapper.findByStatus();
        for(int i =0; i < SIZE; i++){
            //1. 유저의 소속국가와 통화 가져오기
            long gm_id = 760_836L + (long)(Math.random() * 1_000_000);
            UserInfo userInfo = userMapper.findCountryAndCurrency(gm_id);
        }

    }
}
