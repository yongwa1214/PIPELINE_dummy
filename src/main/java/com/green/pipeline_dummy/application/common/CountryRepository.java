package com.green.pipeline_dummy.application.common;

import com.green.pipeline_dummy.entity.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
