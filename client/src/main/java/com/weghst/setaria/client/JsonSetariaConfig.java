package com.weghst.setaria.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.weghst.setaria.client.util.ObjectMapperUtils;

/**
 * @author Kevin Zou <kevinz@weghst.com>
 */
public class JsonSetariaConfig extends AbstractWatchedSetariaConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JsonSetariaConfig.class);
    private ConfigProvider configProvider;

    /**
     * @param setariaBean
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
