package com.green.pipeline_dummy.application.community_workshop;

import com.green.pipeline_dummy.application.community_workshop.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkshopMapper {
    void saveWorkshop(WorkshopDummyDto dto);
    void saveWorkshopMedia(WorkshopMediaDummy dto);
    void saveWorkshopComment(WorkshopCommentDummyDto dto);
    void saveWorkshopBookmark(WorkshopBookmarkDummyDto dto);
    void saveWorkshopFollow(WorkshopFollowDummyDto dto);
    List<Long> findWorkshopIds();
}
