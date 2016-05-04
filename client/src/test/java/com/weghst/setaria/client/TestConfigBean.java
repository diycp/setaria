package com.weghst.setaria.client;

import org.springframework.stereotype.Component;

import com.weghst.setaria.client.annotation.ConfigValue;

@Component
public class TestConfigBean {

    @ConfigValue("${first.key:Default First}")
    public String first;

    @ConfigValue("${second.key:Default Second}")
    public String second;
}
