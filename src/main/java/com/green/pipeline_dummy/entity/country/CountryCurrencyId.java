package com.green.pipeline_dummy.entity.country;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryCurrencyId implements Serializable {

    private String countryCode;
    private String currencyCode;
}
