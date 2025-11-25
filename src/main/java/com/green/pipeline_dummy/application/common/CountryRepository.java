package com.green.pipeline_dummy.application.common;

import com.green.pipeline_dummy.entitiy.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.css.Counter;

public interface CountryRepository extends JpaRepository<Country, String> {
}
