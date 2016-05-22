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
package com.weghst.setaria.console.web;

import java.io.File;
import java.util.EnumSet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.ext.spring.web.WebLogbackConfigurer;

/**
 * Web 配置监听器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@WebListener
public class WebConfigurerListener extends ContextLoaderListener implements ServletContextListener {

    private WebApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        // 初始化 Logback
        sc.setInitParameter(WebLogbackConfigurer.CONFIG_LOCATION_PARAM, findLogbackConfigLocation());
        WebLogbackConfigurer.initLogging(sc);

        // 初始化 Spring 配置
        sc.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "classpath:spring-setaria-console.xml");
        applicationContext = initWebApplicationContext(sc);

        // 注册 Spring Character Encoding
        registerCharacterEncoding(sc);

        // 注册 Spring Servlet
        registerDispatcherServlet(sc);

        // 注册 Spring FreeMarker Servlet 处理 FTL 请求
        registerSpringFreeMarkerServlet(sc);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        closeWebApplicationContext(sce.getServletContext());

        WebLogbackConfigurer.shutdownLogging(sce.getServletContext());
    }

    private String findLogbackConfigLocation() {
        File file = new File(System.getProperty("user.home") + "/.setaria/logback.xml");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return "classpath:logback.xml";
    }

    private void registerCharacterEncoding(ServletContext sc) {
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", false);

        FilterRegistration.Dynamic dynamic = sc.addFilter(CharacterEncodingFilter.class.getName(), filter);
        dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }

    private void registerDispatcherServlet(ServletContext sc) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        ServletRegistration.Dynamic dynamic = sc.addServlet(DispatcherServlet.class.getName(), dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/r/*");
    }

    private void registerSpringFreeMarkerServlet(ServletContext sc) {
        SpringFreeMarkerServlet servlet = new SpringFreeMarkerServlet();
        ServletRegistration.Dynamic dynamic = sc.addServlet(SpringFreeMarkerServlet.class.getName(), servlet);
        dynamic.addMapping("*.ftl");
    }
}
