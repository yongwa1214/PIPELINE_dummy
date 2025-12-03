package com.green.pipeline_dummy.application.game.game_challenge;

import com.green.pipeline_dummy.application.game.game_challenge.model.UserChallengeDummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameChallengeMapper {
    void saveUserChallenge(UserChallengeDummyDto dto);
    List<Long> findChallengeIds(Long gameId);
}
