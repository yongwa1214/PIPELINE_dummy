package com.green.pipeline_dummy.entity.game.mapping;

import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.searchtag.Os;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_os")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameOs {

    @EmbeddedId
    private GameOsId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("osId")
    @JoinColumn(name = "os_id")
    private Os os;
}
