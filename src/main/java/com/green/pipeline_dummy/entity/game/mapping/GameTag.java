package com.green.pipeline_dummy.entity.game.mapping;

import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.searchtag.Tag;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_tag")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameTag {

    @EmbeddedId
    private GameTagId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private Tag tag;
}

