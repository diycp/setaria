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
package com.weghst.setaria.client.web;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weghst.setaria.client.SetariaConfig;
import com.weghst.setaria.client.SetariaConfigContext;
import com.weghst.setaria.client.SetariaConfigException;

/**
 * Web 应用监听器初始化 {@code SetariaConfig}.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class SetariaConfigContextListener implements ServletContextListener {

    /**
     * {@link SetariaConfig} 实现类.
     */
    public static final String SETARIA_CONFIG_IMPLEMENTATION = "setaria.config.implementation";

    private static final Logger LOG = LoggerFactory.getLogger(SetariaConfigContextListener.class);
    private SetariaConfig setariaConfig;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext sc = servletContextEvent.getServletContext();

        String configClass = sc.getInitParameter(SETARIA_CONFIG_IMPLEMENTATION);
        setariaConfig = newSetariaConfig(configClass, getInitParameters(sc));

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

    private Map<String, String> getInitParameters(ServletContext sc) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> parameterNames = sc.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            parameters.put(name, sc.getInitParameter(name));
        }
        return parameters;
    }

    private SetariaConfig newSetariaConfig(String className, Map<String, String> parameters) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(new Class[]{Map.class});
            return (SetariaConfig) constructor.newInstance(parameters);
        } catch (Exception e) {
            throw new SetariaConfigException(e);
        }
    }

}
