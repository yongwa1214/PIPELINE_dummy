package com.green.pipeline_dummy;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // auditing 기능 활성화
public class JpaAuditingConfiguration {
}
