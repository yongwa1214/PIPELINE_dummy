package com.green.pipeline_dummy.entity.game.mapping;

import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.searchtag.Language;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_language")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameLanguage {

    @EmbeddedId
    private GameLanguageId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("languageId")
    @JoinColumn(name = "language_id")
    private Language language;
}

