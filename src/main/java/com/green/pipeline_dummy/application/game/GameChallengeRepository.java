package com.green.pipeline_dummy.application.game;


import com.green.pipeline_dummy.application.game.game_challenge.model.UserChallengeDummyDto;
import com.green.pipeline_dummy.entity.game.GameChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameChallengeRepository extends JpaRepository<GameChallenge, Long> {
    List<GameChallenge> findByGameId(Long gameId);
    void saveUserChallenge(List<UserChallengeDummyDto> dto);
}
