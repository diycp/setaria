package com.weghst.setaria.client;

import com.weghst.setaria.client.spring.ConfigValueBeanPostProcessor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Configuration
@ComponentScan("com.weghst.setaria")
public class SpringConfigurer {

    @Bean
    public ConfigValueBeanPostProcessor configValueBeanPostProcessor(ListableBeanFactory beanFactory) {
        return new ConfigValueBeanPostProcessor(beanFactory);
    }

}
