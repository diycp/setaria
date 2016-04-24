package com.weghst.setaria.client.annotation;

import java.lang.annotation.*;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ConfigValue {

    /**
     * @return
     */
    String value();
}
