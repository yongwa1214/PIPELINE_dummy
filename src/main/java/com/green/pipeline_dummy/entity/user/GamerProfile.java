package com.green.pipeline_dummy.entity.user;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "gamer_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gm_profile_id")
    private Long gmProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "gm_nickname", length = 20, nullable = false)
    private String gmNickname;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "profile_banner", length = 255)
    private String profileBanner;

    @Column(name = "last_login", nullable = false)
    private Timestamp lastLogin;

    @Builder.Default
    @Column(name = "wallet", precision = 10, scale = 2)
    private BigDecimal wallet = BigDecimal.ZERO;
}
