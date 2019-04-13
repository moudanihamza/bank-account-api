package com.talan.kata;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;


@ComponentScan(basePackages ="com.talan.kata" )
@EntityScan(basePackages = "com.talan.kata")
@EnableJpaRepositories
@DataJpaTest
@ActiveProfiles("TEST")
public class ApplicationTestConfig {
}
