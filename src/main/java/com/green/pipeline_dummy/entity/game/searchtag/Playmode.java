package com.green.pipeline_dummy.entity.game.searchtag;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playmode")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Playmode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playmode_id")
    private Long id;

    @Column(name = "playmode_name", length = 50, nullable = false)
    private String name;
}