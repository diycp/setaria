package com.weghst.setaria.client.annotation;

import java.lang.annotation.*;

/**
 * Spring 获取配置注解. 该注解需要在 Spring 中定义 {@link com.weghst.setaria.client.spring.ConfigValueBeanPostProcessor}
 * 处理器才可正常工作.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @see com.weghst.setaria.client.spring.ConfigValueBeanPostProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface ConfigValue {

    /**
     * 配置属性名称及默认值表达式.
     * <p>样例</p>
     * <pre>
     *     &#064;ConfigValue("${config.first}")
     *     private String first;
     *
     *     &#064;ConfigValue("${config.second:Default Value}")
     *     private String second;
     *
     *     &#064;ConfigValue("${config.third:Method Annotation}")
     *     public void setThird(String third) {
     *          // ----
     *     }
     * </pre>
     *
     * @return 属性名称
     */
    String value();
}
