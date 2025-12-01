package com.green.pipeline_dummy.application.purchase;

import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.game.GameMapper;
import com.green.pipeline_dummy.application.game.model.GameIdRes;
import com.green.pipeline_dummy.application.library.LibraryMapper;
import com.green.pipeline_dummy.application.purchase.purchase.PurchaseMapper;
import com.green.pipeline_dummy.application.purchase.purchase.model.PurchaseMethod;
import com.green.pipeline_dummy.application.purchase.purchase_item.model.PurchaseItemDto;
import com.green.pipeline_dummy.application.user.UserMapper;
import com.green.pipeline_dummy.application.user.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseDummy extends MbDummy {
    @Autowired UserMapper userMapper;
    @Autowired GameMapper gameMapper;
    @Autowired PurchaseMapper purchaseMapper;
    @Autowired LibraryMapper libraryMapper;

    @Test
    @Rollback(value = false)
    void savePurchaseProcessUntilProcessDetail(){
        final int SIZE = 100_000;
        List<GameIdRes> gameList = gameMapper.findByStatus();
        for(int i =0; i < SIZE; i++){
            //1. 유저의 소속국가와 통화 가져오기
            long gm_id = 760_836L + (long)(Math.random() * 1_000_000);
            UserInfo userInfo = userMapper.findCountryAndCurrency(gm_id);
            //2. 유저가 사용가능한 결제 수단
            List<PurchaseMethod> purchaseMethod = purchaseMapper.findPurchaseMethod(userInfo.getCountryCode());

            //3. 랜덤한 게임 리스트
            List<Long> randomIds = new ArrayList<>();
            List<GameIdRes> copyList = new ArrayList<>(gameList); // 카피(뽑은 요소 제거하려고)
            int count = 1 + (int)(Math.random() * 4);
            for (int j = 0; j < count; i++) {
                int index = (int)(Math.random() * copyList.size());
                randomIds.add(copyList.get(index).getGameId());
                copyList.remove(index);
            }

            // 만약 라이브러리에 본게임이 없을시 본게임을 추가
            List<Long> missGame = libraryMapper.findMissingBaseGames(randomIds);
            for(Long id : missGame){
                randomIds.add(id);
            }

            // 게임아이디만 상세에 넣어둠
            List<PurchaseItemDto> purchaseItems = randomIds.stream()
                    .map(id -> PurchaseItemDto.builder()
                            .gameId(id)  // gameId만 세팅
                            .build())
                    .collect(Collectors.toList());

        }

    }
}
