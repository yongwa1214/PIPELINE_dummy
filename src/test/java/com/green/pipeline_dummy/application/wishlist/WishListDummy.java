package com.green.pipeline_dummy.application.wishlist;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.cart.WishList;
import com.green.pipeline_dummy.application.cart.model.WishListReq;
import com.green.pipeline_dummy.application.game.GameMapper;
import com.green.pipeline_dummy.application.game.model.GameIdRes;
import com.green.pipeline_dummy.model.RandomDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WishListDummy extends MbDummy {
    @Autowired GameMapper gameMapper;
    @Autowired WishList wishList;

    @Test
    @Rollback(false)
    void saveWishList(){
        final int SIZE = 20_000;
        List<GameIdRes> gameList = gameMapper.findByStatus();
        for(int i = 0 ; i < SIZE; i++ ){
            int num = (int)(Math.random() * SIZE);
            GameIdRes randomGame = gameList.get(num);
            long gm_id = 760_836L + (long)(Math.random() * 500_000);
            RandomDate create = RandomDate.builder()
                    .startYear(2021)
                    .startMonth(1)
                    .startDate(1)
                    .endMonth(11)
                    .endDate(20)
                    .build();
            WishListReq wishListReq = WishListReq.builder()
                    .gameId(randomGame.getGameId())
                    .gmProfileId(gm_id)
                    .createdAt(CommonMethod.randomDateFuture(create))
                    .build();
        }
    }
}
