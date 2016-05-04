package com.weghst.setaria.client;

import org.springframework.stereotype.Component;

import com.weghst.setaria.client.annotation.ConfigValue;

@Component
public class TestConfigBean {

    @ConfigValue("${first.key}")
    public String first;

    @ConfigValue("${second.key}")
    public String second;
}
