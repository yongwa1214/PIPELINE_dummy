package com.green.pipeline_dummy.entity.common;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "code_group")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private String groupId;

    @Column(name = "description", nullable = false)
    private String description;
}
