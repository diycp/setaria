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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象的 {@link SetariaConfig} 实现.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public abstract class AbstractSetariaConfig implements SetariaConfig {

    private List<SetariaConfigListener> listeners = new ArrayList<>();

    private Map<String, String> configParameters;

    /**
     *
     */
    public AbstractSetariaConfig() {
    }

    /**
     * @param configParameters
     */
    protected AbstractSetariaConfig(Map<String, String> configParameters) {
        this.configParameters = new HashMap<>(configParameters);
    }

    @Override
    public final void init() {
        fireEvent(SetariaConfigListener.Event.BEFORE_INIT);

        doInit();

        fireEvent(SetariaConfigListener.Event.AFTER_INIT);
    }

    @Override
    public final void refresh() {
        fireEvent(SetariaConfigListener.Event.BEFORE_REFRESH);

        doRefresh();

        fireEvent(SetariaConfigListener.Event.AFTER_REFRESH);
    }

    @Override
    public final void destroy() {
        fireEvent(SetariaConfigListener.Event.BEFORE_DESTROY);

        doDestroy();

        fireEvent(SetariaConfigListener.Event.AFTER_DESTROY);
    }

    @Override
    public void addListener(SetariaConfigListener listener) {
        listeners.add(listener);
    }

    @Override
    public boolean removeListener(SetariaConfigListener listener) {
        return listeners.remove(listener);
    }

    /**
     * init.
     */
    protected abstract void doInit();

    /**
     * refresh.
     */
    protected abstract void doRefresh();

    /**
     * destory.
     */
    protected abstract void doDestroy();

    /**
     * 通过名称获取初始化参数.
     *
     * @param name 参数名称
     * @return String
     * @throws IllegalArgumentException 如果属性名称不存在对应的初始化参数
     */
    protected String getConfigParameter(String name) throws IllegalArgumentException {
        String parameter = configParameters.get(name);
        if (parameter == null) {
            throw new IllegalArgumentException("未发现配置参数 [" + name + "]");
        }
        return parameter;
    }

    private void fireEvent(SetariaConfigListener.Event event) {
        for (SetariaConfigListener listener : listeners) {
            listener.execute(event);
        }
    }
}
