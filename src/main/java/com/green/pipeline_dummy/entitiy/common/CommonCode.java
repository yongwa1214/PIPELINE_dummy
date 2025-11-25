package com.green.pipeline_dummy.entitiy.common;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "common_code")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCode {

    @Id
    @Column(name = "code_id", length = 10)
    private String codeId;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private CodeGroup codeGroup;

    @Column(name = "code_value", length = 20, nullable = false)
    private String codeValue;

    @Column(name = "description", nullable = false)
    private String description;
}