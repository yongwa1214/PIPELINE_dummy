package com.green.pipeline_dummy.entity.game.searchtag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genre")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(name = "genre_name", length = 50, nullable = false)
    private String name;
}
