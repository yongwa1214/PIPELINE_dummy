package com.green.pipeline_dummy.application.user;

import com.green.pipeline_dummy.CommonMethod;
import com.green.pipeline_dummy.MbDummy;
import com.green.pipeline_dummy.application.friend.FriendMapper;
import com.green.pipeline_dummy.application.friend.model.BlockReq;
import com.green.pipeline_dummy.application.friend.model.FriendReq;
import com.green.pipeline_dummy.model.RandomDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FriendDummy extends MbDummy {
    @Autowired FriendMapper friendMapper;

    @Test
    @Rollback(false)
    void saveFriend(){
        final int SIZE = 20_000;
        String[] status = {"ST-ACPT", "ST-PEND", "ST-REJ"};
        for(int i =0; i < SIZE; i++){
            long sender = 960_836L + (long)(Math.random() * 100_000);
            long receiver = (long)(Math.random()* 100_000)+ 1_060_836L;
            RandomDate create = RandomDate.builder()
                    .startYear(2025)
                    .startMonth(10)
                    .startDate(1)
                    .endMonth(11)
                    .endDate(20)
                    .build();
            RandomDate update = RandomDate.builder()
                    .startYear(2025)
                    .startMonth(11)
                    .startDate(16)
                    .endMonth(11)
                    .endDate(25)
                    .build();
            LocalDateTime createdAt = CommonMethod.randomDateFuture(create);
            LocalDateTime updatedAt =CommonMethod.randomDateFuture(update);
            FriendReq req = FriendReq.builder()
                    .sender(sender)
                    .receiver(receiver)
                    .frStatus(status[2])
                    .createdAt(createdAt)
                    .updatedAt(createdAt.isBefore(updatedAt) ? updatedAt : createdAt)
                    .build();
            friendMapper.saveFriend(req);
        }
    }

    // 차단 목록

    @Test
    @Rollback(false)
    void saveBlock(){
        final int SIZE = 100_000;
        for(int i =0; i < SIZE; i++){
            long sender = 960_836L + (long)(Math.random() * 300_000);
            long receiver = (long)(Math.random()* 300_000)+ 960_836L;
            BlockReq req = BlockReq.builder()
                    .fromUser(sender)
                    .toUser(receiver)
                    .build();
            friendMapper.saveBlock(req);
        }
    }
}
