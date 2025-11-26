package com.green.pipeline_dummy.entity.game.mapping;

import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.searchtag.Playmode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_playmode")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GamePlaymode {

    @EmbeddedId
    private GamePlaymodeId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("playmodeId")
    @JoinColumn(name = "playmode_id")
    private Playmode playmode;
}