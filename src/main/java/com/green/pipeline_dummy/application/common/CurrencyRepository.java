package com.green.pipeline_dummy.application.common;

import com.green.pipeline_dummy.entity.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
