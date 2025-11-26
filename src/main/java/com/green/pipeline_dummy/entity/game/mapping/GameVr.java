package com.green.pipeline_dummy.entity.game.mapping;

import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.searchtag.VrPlatform;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "game_vr")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameVr {

    @EmbeddedId
    private GameVrId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("vrId")
    @JoinColumn(name = "vr_id")
    private VrPlatform vrPlatform;
}
