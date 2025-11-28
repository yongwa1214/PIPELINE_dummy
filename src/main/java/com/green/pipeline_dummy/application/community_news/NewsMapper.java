package com.green.pipeline_dummy.application.community_news;

import com.green.pipeline_dummy.application.community_news.model.NewsDummyDto;
import com.green.pipeline_dummy.application.community_news.model.NewsCommentDummyDto;
import com.green.pipeline_dummy.application.community_news.model.NewsMediaDummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    void saveNews(NewsDummyDto dto);
    void saveNewsComment(NewsCommentDummyDto dto);
    void saveNewsMedia(NewsMediaDummyDto dto);
    List<Long> findNewsIds();
}
