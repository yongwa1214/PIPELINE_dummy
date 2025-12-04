package com.green.pipeline_dummy.application.purchase;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.game.GameMapper;
import com.green.pipeline_dummy.application.game.model.*;
import com.green.pipeline_dummy.application.library.LibraryMapper;
import com.green.pipeline_dummy.application.purchase.purchase.PurchaseMapper;
import com.green.pipeline_dummy.application.purchase.purchase.model.Purchase;
import com.green.pipeline_dummy.application.purchase.purchase.model.PurchaseMethod;
import com.green.pipeline_dummy.application.purchase.purchase_item.PurchaseItemMapper;
import com.green.pipeline_dummy.application.purchase.purchase_item.model.PurchaseItemDto;
import com.green.pipeline_dummy.application.purchase.purchase_item.model.PurchaseItemFinal;
import com.green.pipeline_dummy.application.user.UserMapper;
import com.green.pipeline_dummy.application.user.model.UserInfo;
import com.green.pipeline_dummy.model.RandomDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PurchaseDummy extends MbDummy {
    @Autowired UserMapper userMapper;
    @Autowired GameMapper gameMapper;
    @Autowired PurchaseMapper purchaseMapper;
    @Autowired LibraryMapper libraryMapper;
    @Autowired PurchaseItemMapper purchaseItemMapper;

    @Test
    @Rollback(value = false)
    @Transactional
    void savePurchaseProcessUntilProcessDetail(){
        final int SIZE = 100_000;
//        List<GameIdRes> gameList = gameMapper.findByStatus(); // 활성화된게임
          List<GameIdRes> gameList = gameMapper.findByStatusAndGameType(); // 활성화된 본게임들
        for(int i =0; i < SIZE; i++){
            //1. 유저의 소속국가와 통화 가져오기
            long gm_id = 770_836L + (long)(Math.random() * 100_000);
            UserInfo userInfo = userMapper.findCountryAndCurrency(gm_id); // 유저의 국가 코드와 통화코드가 있음
            //2. 유저가 사용가능한 결제 수단 목록
            List<PurchaseMethod> purchaseMethodList = purchaseMapper.findPurchaseMethod(userInfo.getCountryCode());
            int idx = (int) (Math.random() * purchaseMethodList.size());
            PurchaseMethod purchaseMethod = purchaseMethodList.get(idx);
            //2-1. 유저의 게임 목록
            List<Long> userGameList = libraryMapper.findOwnGame(gm_id);

            //3. 랜덤한 게임 리스트
            List<Long> randomGameList = new ArrayList<>();
            List<GameIdRes> copyList = new ArrayList<>(gameList); // 카피(뽑은 요소 제거하려고)
            int count = 3 + (int)(Math.random() * 7);
            for (int j = 0; j < count; j++) {
                if (copyList.isEmpty()) break;

                int index = (int)(Math.random() * copyList.size());
                long gameId = copyList.get(index).getGameId();

                // ⭐ 이미 유저가 가지고 있으면 PASS
                if (userGameList.contains(gameId)) {
                    copyList.remove(index);
                    j--; // 다시 뽑기 위해 인덱스 유지
                    continue;
                }

                randomGameList.add(gameId);
                copyList.remove(index);
            }

            // 라이브러리에 본게임이 없을시 본게임을 추가
            List<Long> missGame = libraryMapper.findMissingBaseGames(randomGameList);
            for(Long id : missGame){
                randomGameList.add(id);
            }

            //게임들의 원가격
            PriceDto priceDto = PriceDto.builder()
                    .countryCode(userInfo.getCountryCode())
                    .gameList(randomGameList)
                    .build();

            List<PriceIdRes> priceList = gameMapper.findGamePrice(priceDto);

            //할인id 및 할인율 가져오기
            RandomDate randomDate = RandomDate.builder()
                    .startYear(2025)
                    .startMonth(6)
                    .startDate(20)
                    .endMonth(7)
                    .endDate(5)
                    .build();

            DiscountDto discountDto = DiscountDto.builder()
                    .date(CommonMethod.randomDateFuture(randomDate))
                    .gameList(randomGameList)
                    .build();
            List<DiscountRes> discountList = gameMapper.findDiscount(discountDto);
            int idf =0;

            //전체 할인 금액
            BigDecimal totalDiscount = BigDecimal.ZERO;
            for (int l = 0; l < priceList.size(); l++) {
                BigDecimal price = priceList.get(l).getPrice_amount();
                BigDecimal rate  = discountList.get(l).getDiscountRate();

                if (rate.compareTo(BigDecimal.ZERO) > 0) { // 할인율이 0보다 클 때만
                    BigDecimal discount = price.multiply(rate).multiply(BigDecimal.valueOf(0.01));
                    totalDiscount = totalDiscount.add(discount);
                }
            }

            //전체 금액
            BigDecimal totalPrice = BigDecimal.ZERO;

            for (PriceIdRes res : priceList) {
                totalPrice = totalPrice.add(res.getPrice_amount());
            }

            Purchase finalPurchase = Purchase.builder()
                    .gmProfileId(gm_id)
                    .methodId(purchaseMethod.getMethodId())
                    .currencyCode(userInfo.getCurrencyCode())
                    .discountPrice(totalDiscount)
                    .walletUsed(CommonMethod.walletCurrency(userInfo.getCurrencyCode()))
                    .paidPrice(totalPrice)
                    .purchaseDate(discountDto.getDate())
                    .pgTid(CommonMethod.createName(20))
                    .build();

            purchaseMapper.savePurchase(finalPurchase);

            List<PurchaseItemDto> purchaseItemList = new ArrayList<>();

            for (int k = 0; k < randomGameList.size(); k++) {
                Long gameId = randomGameList.get(k);
                BigDecimal basePrice = priceList.get(k).getPrice_amount();
                DiscountRes discount = discountList.get(k);

                BigDecimal discountAmount = basePrice.multiply(discount.getDiscountRate());
                BigDecimal finalPrice = basePrice.subtract(discountAmount);

                PurchaseItemDto item = PurchaseItemDto.builder()
                        .gameId(gameId)
                        .basePrice(basePrice)
                        .discountId(discount.getDiscountId() != null ? discount.getDiscountId() : null)
                        .finalPrice(finalPrice)
                        .purchaseType("ST-OWN")
                        .itemStatus("ST-ACPT")
                        .build();

                purchaseItemList.add(item);
            }

            // PurchaseItemFinal 생성
            PurchaseItemFinal purchaseItemFinal = PurchaseItemFinal.builder()
                    .purchaseId(finalPurchase.getPurchaseId()) // 실제 purchaseId 넣기
                    .purchaseItem(purchaseItemList)
                    .updatedAt(finalPurchase.getPurchaseDate())
                    .build();


            purchaseItemMapper.saveDetail(purchaseItemFinal);
        }


    }
}
