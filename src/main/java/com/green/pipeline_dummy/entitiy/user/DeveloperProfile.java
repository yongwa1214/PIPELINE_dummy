package com.green.pipeline_dummy.entitiy.user;

import com.green.pipeline_dummy.entitiy.common.CommonCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "developer_profile")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_profile_id")
    private Long dpProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;   // USER 테이블 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_type", nullable = false)
    private CommonCode entityType;  // common_code 테이블 엔티티

    @Column(name = "developer_name", length = 20, nullable = false)
    private String developerName;

    @Column(name = "web_url", length = 500)
    private String webUrl;

    @Column(name = "post_code", length = 20, nullable = false)
    private String postCode;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "address_detail", length = 100, nullable = false)
    private String addressDetail;

    @Column(name = "phone", length = 30, nullable = false)
    private String phone;
}