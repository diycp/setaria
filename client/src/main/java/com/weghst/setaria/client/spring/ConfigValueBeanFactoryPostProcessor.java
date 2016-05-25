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
package com.weghst.setaria.client.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import com.weghst.setaria.client.Configs;

/**
 * {@link com.weghst.setaria.client.annotation.ConfigValue} 注解处理器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ConfigValueBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    /**
     * 是否已经注册 ConfigValueBeanPostProcessor 处理器.
     */
    private boolean done;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        if (!done) {
            postProcess(beanFactory);
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        postProcess((ConfigurableListableBeanFactory) registry);
        done = true;
    }

    public void postProcess(ConfigurableListableBeanFactory beanFactory) {
        // 注册 Spring 属性配置
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        MutablePropertySources mutablePropertySources = new MutablePropertySources();
        mutablePropertySources.addLast(new PropertySource<String>(Configs.class.getName()) {
            @Override
            public String getProperty(String name) {
                return Configs.getString(name);
            }
        });
        configurer.setPropertySources(mutablePropertySources);
        configurer.postProcessBeanFactory(beanFactory);

        /*
         * 注册 @ConfigValue 处理器. ConfigValueBeanPostProcessor 实现了 ApplicationListener 接口, 不能使用
         * beanFactory.addBeanPostProcessor() 来注册实例.
         */
        beanFactory.registerSingleton(ConfigValueBeanPostProcessor.class.getName(),
                new ConfigValueBeanPostProcessor(beanFactory));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
