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

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SpringFreeMarkerServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SpringFreeMarkerServlet.class);

    private FreeMarkerViewResolver freeMarkerViewResolver;
    private String suffix;
    private boolean inited;

    @Override
    public void init() throws ServletException {
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(
                getServletContext());
        freeMarkerViewResolver = applicationContext.getBean(FreeMarkerViewResolver.class);

        Method method = ReflectionUtils.findMethod(FreeMarkerViewResolver.class, "getSuffix");
        ReflectionUtils.makeAccessible(method);
        suffix = ReflectionUtils.invokeMethod(method, freeMarkerViewResolver).toString();

        inited = true;

        LOG.debug("spring freemarker servlet initialized, freemarker suffix [{}]", suffix);
    }

    @Override
    public void destroy() {
        freeMarkerViewResolver = null;
        inited = false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!inited) {
            LOG.error("spring freemarker servlet 未初始化");
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.getWriter().write("spring freemarker servlet no init");
            return;
        }

        try {
            String requestUri = req.getRequestURI();
            String templatePath = requestUri.substring(0, requestUri.length() - suffix.length());
            View view = freeMarkerViewResolver.resolveViewName(templatePath, req.getLocale());
            view.render(null, req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace(new PrintStream(resp.getOutputStream()));
        }
    }

}
