package com.green.pipeline_dummy.entity.game.mapping;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameTagId implements Serializable {
    private Long gameId;
    private Long tagId;
}

