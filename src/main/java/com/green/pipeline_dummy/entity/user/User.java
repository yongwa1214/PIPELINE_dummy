package com.green.pipeline_dummy.entity.user;

import com.green.pipeline_dummy.entity.common.CommonCode;
import com.green.pipeline_dummy.entity.country.Country;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`user`") // 예약어 충돌 방지
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "country_code", nullable = false)
    private Country countryCode;

    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_role", nullable = false)
    private CommonCode userRole;

    @Column(name = "created_at", nullable = false)
    private java.sql.Timestamp createdAt;
}