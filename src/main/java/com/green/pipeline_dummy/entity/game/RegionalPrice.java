package com.green.pipeline_dummy.entity.game;

import com.green.pipeline_dummy.entity.country.Country;
import com.green.pipeline_dummy.entity.currency.Currency;
import com.green.pipeline_dummy.entity.game.Game;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "regional_price",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_game_country",
                        columnNames = {"game_id", "country_code"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionalPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regional_price_id")
    private Long id;

    // 국가 코드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
    private Country country;

    // 통화 코드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_code")
    private Currency currency;

    // 게임
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    // 가격
    @Column(name = "price_amount", nullable = false)
    private BigDecimal priceAmount;

    // 생성일 (DB 기본값 now())
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}