package com.weghst.setaria.core;

import com.weghst.setaria.core.Constants;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Component(DatabaseProcessor.BEAN_NAME)
public class DatabaseProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseProcessor.class);
    /**
     * DatabaseProcessor 被 Ioc 容器所管理的 ID。
     */
    public static final String BEAN_NAME = "com.weghst.setaria.DatabaseProcessor";

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        LOG.debug("Setaria database init");

        boolean autoUpgrade = Boolean.getBoolean(Constants.AUTO_UPGRADE_DATABASE);
        if (!autoUpgrade) {
            LOG.info("自动升级数据库脚本被关闭");
            return;
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }

}
