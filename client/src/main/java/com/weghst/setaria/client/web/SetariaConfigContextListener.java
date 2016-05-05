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
            parameters.put(parameterNames.nextElement(), sc.getInitParameter(parameterNames.nextElement()));
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
