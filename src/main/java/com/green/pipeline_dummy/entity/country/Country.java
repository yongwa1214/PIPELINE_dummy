package com.green.pipeline_dummy.entity.country;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @Id
    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "country_name", length = 50, nullable = false)
    private String countryName;

    @Column(name = "country_num", nullable = false)
    private Integer countryNum;

    @Column(name = "country_tel", nullable = false)
    private Integer countryTel;
}