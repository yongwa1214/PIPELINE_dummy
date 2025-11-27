package com.green.pipeline_dummy.application.community_news;

import com.green.pipeline_dummy.application.community_news.model.NewsDummyDto;
import com.green.pipeline_dummy.application.community_news.model.NewsCommentDummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    void saveNews(NewsDummyDto dto);
    void saveNewsComment(NewsCommentDummyDto dto);
    List<Long> findNewsIds();
}
