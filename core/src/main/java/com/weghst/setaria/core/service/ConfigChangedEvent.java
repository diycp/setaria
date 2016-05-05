package com.weghst.setaria.core.service;

import org.springframework.context.ApplicationEvent;

/**
 * 配置改变事件. 当应用的增加/修改/删除某个配置时就触发 Spring ApplicationEvent 事件.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 * @see ApplicationEvent
 * @see org.springframework.context.ApplicationEventPublisher
 */
public class ConfigChangedEvent extends ApplicationEvent {

    /**
     * 通过事件源创建事件对象.
     *
     * @param source 事件源对象
     */
    public ConfigChangedEvent(Object source) {
        super(source);
    }
}
