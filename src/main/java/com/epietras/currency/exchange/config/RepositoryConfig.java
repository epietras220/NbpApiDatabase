package com.epietras.currency.exchange.config;

import com.epietras.currency.exchange.repository.config.BaseRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.epietras.currency.exchange.model"})
@EnableJpaRepositories(
        basePackages = {"com.epietras.currency.exchange.repository.config"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableTransactionManagement
public class RepositoryConfig {
}
