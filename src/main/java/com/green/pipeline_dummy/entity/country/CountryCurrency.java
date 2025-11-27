package com.green.pipeline_dummy.entity.country;

import com.green.pipeline_dummy.entity.country.Country;
import com.green.pipeline_dummy.entity.currency.Currency;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country_currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryCurrency {

    @EmbeddedId
    private CountryCurrencyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("countryCode")
    @JoinColumn(name = "country_code")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("currencyCode")
    @JoinColumn(name = "currency_code")
    private Currency currency;

    @Column(name = "is_primary")
    private Boolean isPrimary;
}
