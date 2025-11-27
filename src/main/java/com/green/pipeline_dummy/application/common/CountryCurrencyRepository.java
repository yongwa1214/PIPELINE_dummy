package com.green.pipeline_dummy.application.common;

import com.green.pipeline_dummy.entity.country.CountryCurrency;
import com.green.pipeline_dummy.entity.country.CountryCurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryCurrencyRepository
        extends JpaRepository<CountryCurrency, CountryCurrencyId> {

    // 특정 나라의 통화를 모두 조회
    List<CountryCurrency> findByCountry_CountryCode(String countryCode);

    // 특정 나라 + 특정 통화 존재 여부
    boolean existsByCountry_CountryCodeAndCurrency_CurrencyCode(
            String countryCode,
            String currencyCode
    );
}

