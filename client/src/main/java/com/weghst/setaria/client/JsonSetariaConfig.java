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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.weghst.setaria.client.util.ObjectMapperUtils;

/**
 * JSON 文件配置实现.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class JsonSetariaConfig extends AbstractWatchedSetariaConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JsonSetariaConfig.class);
    private ConfigProvider configProvider;

    /**
     * 通过配置基本参数构建实例.
     *
     * @param setariaBean 配置文件参数
     */
    public JsonSetariaConfig(SetariaBean setariaBean) {
        super(setariaBean);
    }

    protected Properties toProperties(Collection<ConfigBean> configBeans) {
        Properties properties = new Properties();
        for (ConfigBean configBean : configBeans) {
            properties.setProperty(configBean.getKey(), configBean.getValue());
        }
        return properties;
    }

    @Override
    protected void doRefresh() {
        Set<ConfigBean> configBeanSet = new HashSet<>();

        List<SetariaBean.Resource> resources = getSetariaBean().getResources();

        for (int i = resources.size() - 1; i >= 0; i--) {
            SetariaBean.Resource resource = resources.get(i);
            try {
                File file = ResourceUtils.getFile(resource.getLocation());
                ConfigBean[] configBeanArray = ObjectMapperUtils.readValue(file, ConfigBean[].class);

                configBeanSet.addAll(Arrays.asList(configBeanArray));
            } catch (FileNotFoundException e) {
                if (resource.isIgnoreNotFound()) {
                    LOG.info("文件 [{}] 不存在, 已忽略", resource.getLocation());
                } else {
                    throw new SetariaConfigException(e);
                }
            }
        }

        configProvider = new DefaultConfigProvider(toProperties(configBeanSet));
    }

    @Override
    protected void doDestroy() {
    }

    @Override
    public ConfigProvider getConfigProvider() {
        return configProvider;
    }
}
