package com.weghst.setaria.console.web;

import ch.qos.logback.ext.spring.web.WebLogbackConfigurer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.EnumSet;

/**
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
        dynamic.addMapping("/p/*");
    }
}
