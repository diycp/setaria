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
