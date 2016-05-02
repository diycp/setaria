package com.weghst.setaria.core;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Component
public class DatabaseProcessor implements BeanPostProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("dataSource".equals(beanName)) {
            upgradeDatabase((DataSource) bean);
        }
        return bean;
    }

    private void upgradeDatabase(DataSource dataSource) {
        LOG.debug("Setaria database init");

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }
}
