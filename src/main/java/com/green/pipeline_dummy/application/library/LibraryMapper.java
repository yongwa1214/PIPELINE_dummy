package com.green.pipeline_dummy.application.library;

import com.green.pipeline_dummy.application.game.model.GameIdRes;
import com.green.pipeline_dummy.application.library.model.LibraryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LibraryMapper {
    List<Long> findMissingBaseGames(List<Long> res);
    void addLibrary(List<LibraryDto> res);
    List<Long> findLibraryIds(Long ownerId);
}
