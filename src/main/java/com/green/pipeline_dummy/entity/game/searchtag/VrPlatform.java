package com.green.pipeline_dummy.entity.game.searchtag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vr_platform")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VrPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vr_id")
    private Long id;

    @Column(name = "vr_name", length = 50, nullable = false)
    private String name;
}