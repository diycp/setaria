/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.setaria.core;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 数据库版本管理处理器. {@code DatabaseProcessor} 依赖于 {@link DataSource} bean 对象初始化完成. 该对象由 Spring
 * 管理时的 {@code id} 必须设置为 {@code dataSource}.
 *
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
        flyway.setValidateOnMigrate(false);
        flyway.migrate();
    }
}
