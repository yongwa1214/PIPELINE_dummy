package com.green.pipeline_dummy.application.community_review;

import com.green.pipeline_dummy.application.community_review.model.ReviewCommentDummyDto;
import com.green.pipeline_dummy.application.community_review.model.ReviewDummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void saveReview(ReviewDummyDto dto);
    void saveReviewComment(ReviewCommentDummyDto dto);
    List<Long> findReview();
}
