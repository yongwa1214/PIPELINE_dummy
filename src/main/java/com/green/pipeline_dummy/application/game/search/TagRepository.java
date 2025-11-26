package com.green.pipeline_dummy.application.game.search;

import com.green.pipeline_dummy.entity.game.searchtag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
