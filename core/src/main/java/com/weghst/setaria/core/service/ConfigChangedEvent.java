package com.weghst.setaria.core.service;

import org.springframework.context.ApplicationEvent;

/**
 * Created by kevin on 2016/5/4.
 */
public class ConfigChangedEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ConfigChangedEvent(Object source) {
        super(source);
    }
}
