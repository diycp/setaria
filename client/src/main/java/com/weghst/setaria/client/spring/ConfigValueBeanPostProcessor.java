package com.weghst.setaria.client.spring;

import com.weghst.setaria.client.Configs;
import com.weghst.setaria.client.SetariaConfig;
import com.weghst.setaria.client.SetariaConfigContext;
import com.weghst.setaria.client.SetariaConfigListener;
import com.weghst.setaria.client.annotation.ConfigValue;
import org.springframework.beans.BeansException;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class ConfigValueBeanPostProcessor implements BeanPostProcessor, PriorityOrdered, ApplicationListener<ApplicationContextEvent> {

    private final SimpleTypeConverter typeConverter = new SimpleTypeConverter();
    private final PropertySourcesPropertyResolver propertyResolver = new PropertySourcesPropertyResolver(
            new ConfigPropertySources());

    private SetariaConfigListener setariaConfigListener = new RefreshedSetariaConfigListener();
    private ListableBeanFactory beanFactory;

    /**
     *
     */
    public ConfigValueBeanPostProcessor(ListableBeanFactory beanFactory) {
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
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                ConfigValue configValue = AnnotationUtils.getAnnotation(field, ConfigValue.class);
                if (configValue == null) {
                    return;
                }

                String str = propertyResolver.resolvePlaceholders(configValue.value());
                Object newValue = typeConverter.convertIfNecessary(str, field.getType());

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
     *
     */
    private class RefreshedSetariaConfigListener implements SetariaConfigListener {

        @Override
        public void execute(Event event) {
            // 配置更新完成后自动刷新 Spring 所管理的 Bean 对象
            if (event == Event.AFTER_REFRESH) {
                for (String beanName : beanFactory.getBeanDefinitionNames()) {
                    doPostProcessInitialization(beanFactory.getBean(beanName));
                }
            }
        }
    }

    private class ConfigPropertySources implements PropertySources {

        PropertySource<?> configPropertySource = new PropertySource<Object>(Configs.class.getName()) {

            @Override
            public Object getProperty(String name) {
                return Configs.getString(name);
            }
        };
        List<?> propertySourceList = Arrays.asList(configPropertySource);

        @Override
        public boolean contains(String name) {
            return configPropertySource.getName().equals(name);
        }

        @Override
        public PropertySource<?> get(String name) {
            if (configPropertySource.getName().equals(name)) {
                return configPropertySource;
            }
            return null;
        }

        @Override
        public Iterator<PropertySource<?>> iterator() {
            return (Iterator<PropertySource<?>>) propertySourceList.iterator();
        }
    }

}
