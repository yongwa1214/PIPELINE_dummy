package com.green.pipeline_dummy.application.game.session;

import com.green.pipeline_dummy.application.game.session.dto.GameSessionLogDto;
import com.green.pipeline_dummy.application.game.game_challenge.model.LibraryForChallengeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameSessionLogMapper {

    int insertGameSession(GameSessionLogDto dto);

    // 라이브러리 최소 정보 가져오기 (같은 DTO 재사용 가능)
    List<LibraryForChallengeDto> getLibrariesForSession();
}
