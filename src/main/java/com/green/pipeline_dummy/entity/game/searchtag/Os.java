package com.green.pipeline_dummy.entity.game.searchtag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "os")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Os {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "os_id")
    private Long id;

    @Column(name = "os_name", length = 50, nullable = false)
    private String name;
}
