/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.setaria.client.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.weghst.setaria.client.SetariaBean;
import com.weghst.setaria.client.SetariaConfig;
import com.weghst.setaria.client.SetariaConfigContext;
import com.weghst.setaria.client.SetariaConfigException;
import com.weghst.setaria.client.util.ObjectMapperUtils;

/**
 * Web 应用监听器初始化 {@code SetariaConfig}.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class SetariaConfigContextListener implements ServletContextListener {

    private static final String DEFAULT_SETARIA_CONFIG_LOCATION = "classpath:setaria.json";

    /**
     * {@link SetariaConfig} 实现类.
     */
    public static final String SETARIA_CONFIG_IMPLEMENTATION = "setaria.config.implementation";
    /**
     *
     */
    public static final String SETARIA_CONFIG_LOCATION = "setaria.config.location";

    private static final Logger LOG = LoggerFactory.getLogger(SetariaConfigContextListener.class);
    private SetariaConfig setariaConfig;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext sc = servletContextEvent.getServletContext();

        String configClass = sc.getInitParameter(SETARIA_CONFIG_IMPLEMENTATION);
        setariaConfig = newSetariaConfig(configClass, getSetariaBean(sc));

        // 初始化配置
        LOG.debug("初始化 SetariaConfig");
        setariaConfig.init();

        // 设置 SetariaConfig 上下文变量
        SetariaConfigContext.setSetariaConfig(setariaConfig);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (setariaConfig != null) {
            LOG.debug("销毁 SetariaConfig");
            setariaConfig.destroy();
        }
    }

    private SetariaBean getSetariaBean(ServletContext sc) {
        String location = sc.getInitParameter(SETARIA_CONFIG_LOCATION);
        if (location == null || location.isEmpty()) {
            location = DEFAULT_SETARIA_CONFIG_LOCATION;
        }

        try {
            File file = ResourceUtils.getFile(location);
            return ObjectMapperUtils.readValue(file, SetariaBean.class);
        } catch (FileNotFoundException e) {
            throw new SetariaConfigException("未找到文件 [" + location + "]", e);
        }
    }

    private SetariaConfig newSetariaConfig(String className, SetariaBean setariaBean) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(new Class[]{SetariaBean.class});
            return (SetariaConfig) constructor.newInstance(setariaBean);
        } catch (Exception e) {
            throw new SetariaConfigException(e);
        }
    }

}
