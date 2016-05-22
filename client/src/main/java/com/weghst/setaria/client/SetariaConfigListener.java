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
package com.weghst.setaria.client;

import java.util.EventListener;

/**
 * 配置监听器接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface SetariaConfigListener extends EventListener {

    /**
     * 监听事件.
     */
    enum Event {
        /**
         * {@link SetariaConfig#init()} 开始事件.
         */
        BEFORE_INIT,
        /**
         * {@link SetariaConfig#init()} 结束事件.
         */
        AFTER_INIT,
        /**
         * {@link SetariaConfig#refresh()} 开始事件.
         */
        BEFORE_REFRESH,
        /**
         * {@link SetariaConfig#refresh()} 结束事件.
         */
        AFTER_REFRESH,
        /**
         * {@link SetariaConfig#destroy()} 开始事件.
         */
        BEFORE_DESTROY,
        /**
         * {@link SetariaConfig#destroy()} 结束事件.
         */
        AFTER_DESTROY
    }

    /**
     * 执行.
     *
     * @param event 事件
     */
    void execute(Event event);
}
