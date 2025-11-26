package com.green.pipeline_dummy.entity.game.mapping;

import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.searchtag.Genre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_genre")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameGenre {

    @EmbeddedId
    private GameGenreId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genre_id")
    private Genre genre;
}

