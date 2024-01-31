package com.example.jpanext.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
// Entity Auditing 기능 설정 (엔티티 객체의 상태 변화를 검사하겠다)
@EnableJpaAuditing
public class JpaConfig {
}
