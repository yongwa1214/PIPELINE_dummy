package com.green.pipeline_dummy.application.game;


import com.green.pipeline_dummy.entity.game.RegionalPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionalPriceRepository extends JpaRepository<RegionalPrice, Long> {

    boolean existsByGame_GameIdAndCountry_CountryCode(Long gameId, String countryCode);

}
