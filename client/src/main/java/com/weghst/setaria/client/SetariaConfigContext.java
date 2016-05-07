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

/**
 * {@link SetariaConfig} 上下文对象.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class SetariaConfigContext {

    private static SetariaConfig setariaConfig;

    private SetariaConfigContext() {
    }

    /**
     * {@link SetariaConfig} 上下文对象.
     *
     * @return {@link SetariaConfig}
     * @throws IllegalStateException 如果上下文中不存在 {@link SetariaConfig}
     */
    public static SetariaConfig getSetariaConfig() throws IllegalStateException {
        if (setariaConfig == null) {
            throw new IllegalStateException("未找到可用的 SetariaConfig 对象, 请确保 SetariaConfig 已初始化并通过" +
                    " SetariaConfigContext.setSetariaConfig(obj) 更新至上下文中");
        }
        return setariaConfig;
    }

    /**
     * 设置 {@link SetariaConfig} 上下文对象, 一般在应用启动中执行该方法.
     *
     * @param setariaConfig {@link SetariaConfig} 上下文对象
     */
    public static void setSetariaConfig(SetariaConfig setariaConfig) {
        SetariaConfigContext.setariaConfig = setariaConfig;
    }

}
