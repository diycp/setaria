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
package com.weghst.setaria.client.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import com.weghst.setaria.client.SetariaConfig;
import com.weghst.setaria.client.SetariaConfigContext;
import com.weghst.setaria.client.SetariaConfigListener;
import com.weghst.setaria.client.annotation.ConfigValue;

/**
 * {@link ConfigValue} 注解处理器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
class ConfigValueBeanPostProcessor implements BeanPostProcessor, PriorityOrdered,
        ApplicationListener<ApplicationContextEvent> {

    private SetariaConfigListener setariaConfigListener = new RefreshedSetariaConfigListener();
    private ConfigurableListableBeanFactory beanFactory;

    /**
     * 通过 {@link ListableBeanFactory} 创建处理器实例.
     *
     * @param beanFactory {@link ConfigurableListableBeanFactory}
     */
    ConfigValueBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        doPostProcessInitialization(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private void doPostProcessInitialization(final Object bean) {
        Class<?> clazz = bean.getClass();
        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {

            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                ConfigValue configValue = AnnotationUtils.getAnnotation(method, ConfigValue.class);
                if (configValue == null) {
                    return;
                }

                if (method.getParameterTypes().length != 1) {
                    throw new IllegalStateException("@ConfigValue 注解只能用于 1 个参数的方法");
                }

                String str = beanFactory.resolveEmbeddedValue(configValue.value());
                Object newValue = beanFactory.getTypeConverter().convertIfNecessary(str, method.getParameterTypes()[0]);
                ReflectionUtils.invokeMethod(method, bean, newValue);
            }
        });

        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                ConfigValue configValue = AnnotationUtils.getAnnotation(field, ConfigValue.class);
                if (configValue == null) {
                    return;
                }

                String str = beanFactory.resolveEmbeddedValue(configValue.value());
                Object newValue = beanFactory.getTypeConverter().convertIfNecessary(str, field.getType());

                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, bean, newValue);
            }
        });
    }

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            SetariaConfig setariaConfig = SetariaConfigContext.getSetariaConfig();
            setariaConfig.addListener(setariaConfigListener);
        } else if (event instanceof ContextClosedEvent) {
            SetariaConfig setariaConfig = SetariaConfigContext.getSetariaConfig();
            setariaConfig.removeListener(setariaConfigListener);
        }
    }

    /**
     * {@link SetariaConfig} 配置刷新监听器, 用于刷新 Spring 所管理对象的配置属性值.
     */
    private class RefreshedSetariaConfigListener implements SetariaConfigListener {

        @Override
        public void execute(Event event) {
            // 配置更新完成后自动刷新 Spring 所管理的 Bean 对象
            if (event == Event.AFTER_REFRESH) {
                for (String beanName : beanFactory.getBeanDefinitionNames()) {
//                    beanFactory.getBean(beanName);
                    doPostProcessInitialization(beanFactory.getBean(beanName));
                }
            }
        }
    }
}
