package com.green.pipeline_dummy.application.community_forum;

import com.green.pipeline_dummy.application.community_forum.model.ForumBookmarkDummyDto;
import com.green.pipeline_dummy.application.community_forum.model.ForumCommentDummyDto;
import com.green.pipeline_dummy.application.community_forum.model.ForumDummyDto;
import com.green.pipeline_dummy.application.community_forum.model.ForumMediaDummyDto;
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
