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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * Properties 文件配置.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class PropertiesSetariaConfig extends AbstractWatchedSetariaConfig {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesSetariaConfig.class);
    private ConfigProvider configProvider;

    public PropertiesSetariaConfig(SetariaBean setariaBean) {
        super(setariaBean);
    }

    @Override
    protected void doRefresh() {
        Properties properties = new Properties();
        for (SetariaBean.Resource resource : getSetariaBean().getResources()) {
            try {
                URL url = ResourceUtils.getURL(resource.getLocation());
                properties.putAll(loadProperties(url));
            } catch (FileNotFoundException e) {
                if (resource.isIgnoreNotFound()) {
                    LOG.info("文件 [{}] 不存在, 已忽略", resource.getLocation());
                } else {
                    throw new IllegalStateException("未发现配置文件 [" + resource.getLocation() + "]", e);
                }
            }
        }

        configProvider = new DefaultConfigProvider(properties);
    }

    @Override
    protected void doDestroy() {
    }

    @Override
    public ConfigProvider getConfigProvider() {
        return configProvider;
    }

    private Properties loadProperties(URL url) {
        InputStream inputStream = null;

        try {
            inputStream = url.openStream();
            Properties properties = new Properties();

            if (url.getPath().endsWith(".properties")) {
                properties.load(inputStream);
            } else if (url.getPath().endsWith(".xml")) {
                properties.loadFromXML(inputStream);
            } else {
                throw new IllegalArgumentException("不支持的文件 [" + url
                        + "], PropertiesSetariaConfig 只支持加载 [*.properties, *.xml] 的 properties 文件");
            }
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("读取配置文件 [" + url + "]错误", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

}
