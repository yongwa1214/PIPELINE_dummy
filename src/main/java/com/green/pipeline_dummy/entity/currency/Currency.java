package com.green.pipeline_dummy.entity.currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
public class Currency {

    @Id
    @Column(name = "currency_code", length = 10, nullable = false)
    private String currencyCode;

    @Column(name = "currency_name", length = 30, nullable = false)
    private String currencyName;

    @Column(name = "symbol", length = 10, nullable = false)
    private String symbol;

    @Column(name = "decimal_digits", nullable = false)
    private Integer decimalDigits;
}
