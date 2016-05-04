package com.weghst.setaria.client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import com.weghst.setaria.client.annotation.ConfigValue;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class DistributedSetariaConfigTest {

    @Test
    public void testExecute() {

        Thread t = new Thread() {
            @Override public void run() {
                Map<String, String> params = new HashMap<>();
                params.put(DistributedSetariaConfig.CONFIG_ZOOKEEPER_CONNECT_STRING, "127.0.0.1:2181");
                params.put(DistributedSetariaConfig.CONFIG_APP, "weghst.pine");
                params.put(DistributedSetariaConfig.CONFIG_ENV, "developer");

                DistributedSetariaConfig setariaConfig = new DistributedSetariaConfig(params);
                setariaConfig.init();

                // 设置上下文
                SetariaConfigContext.setSetariaConfig(setariaConfig);

                ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigurer.class);
                TestConfigBean testConfigBean = applicationContext.getBean(TestConfigBean.class);
                System.out.println(testConfigBean.first);
                System.out.println(testConfigBean.second);
            }
        };

        try {
            t.start();
            Thread.sleep(1000 * 60 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecute2() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }
}