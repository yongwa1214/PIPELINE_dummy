package com.green.pipeline_dummy.application.community;

import com.green.pipeline_dummy.application.community.model.ForumBookmarkDummyDto;
import com.green.pipeline_dummy.application.community.model.ForumCommentDummyDto;
import com.green.pipeline_dummy.application.community.model.ForumDummyDto;
import com.green.pipeline_dummy.application.community.model.ForumMediaDummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ForumMapper {
    void saveForum(ForumDummyDto dto);
    void saveForumComment(ForumCommentDummyDto dto);
    void saveForumMedia(ForumMediaDummyDto dtd);
    void saveForumBookMark(ForumBookmarkDummyDto dto);
    List<Long> findGameIds();
    List<Long> findUserIds();
    List<Long> findForumIds();
    List<Long> findGamerIds();
    int countPinnedByGameId(Long gameId);
}
