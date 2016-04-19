package com.weghst.setaria.core.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class SetariaContextListener implements ServletContextListener {

    public static final String CONFIG_URL = "easyconfig.url";
    public static final String CONFIG_APP = "easyconfig.app";
    public static final String CONFIG_ENV = "easyconfig.env";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext sc = servletContextEvent.getServletContext();

        // http, file, classpath
        String url = sc.getInitParameter(CONFIG_URL);
        String app = sc.getInitParameter(CONFIG_APP);
        String env = sc.getInitParameter(CONFIG_ENV);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
