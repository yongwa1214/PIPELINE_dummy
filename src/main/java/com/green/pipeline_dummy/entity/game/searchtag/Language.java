package com.green.pipeline_dummy.entity.game.searchtag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(name = "language_name", length = 50, nullable = false)
    private String name;

    @Column(name = "code", length = 10, nullable = false)
    private String code;
}

