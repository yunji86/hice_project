package com.greedy.coffee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.greedy.coffee"})
@EnableJpaRepositories(basePackages = "com.greedy.coffee")
public class JPAConfiguration {

}
