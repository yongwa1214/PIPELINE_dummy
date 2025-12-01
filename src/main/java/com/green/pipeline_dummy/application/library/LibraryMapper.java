package com.green.pipeline_dummy.application.library;

import com.green.pipeline_dummy.application.game.model.GameIdRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LibraryMapper {
    List<Long> findMissingBaseGames(List<Long> res);
}
