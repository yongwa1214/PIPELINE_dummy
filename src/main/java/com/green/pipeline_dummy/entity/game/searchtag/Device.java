package com.green.pipeline_dummy.entity.game.searchtag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "device")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long id;

    @Column(name = "device_name", length = 50, nullable = false)
    private String name;
}
