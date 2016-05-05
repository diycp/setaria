package com.weghst.setaria.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

/**
 * Properties 文件配置.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class FileSetariaConfig extends AbstractSetariaConfig {

    /**
     * 配置文件路径 {@link ResourceUtils}.
     */
    public static final String SETARIA_CONFIG_LOCATION = "setaria.config.location";
    private ConfigProvider configProvider;

    public FileSetariaConfig(Map<String, String> configParameters) {
        super(configParameters);
    }

    @Override
    protected void doInit() {
        configProvider = new DefaultConfigProvider(loadProperties());
    }

    @Override
    protected void doRefresh() {
        doInit();
    }

    @Override
    protected void doDestroy() {
        // none
    }

    @Override
    public ConfigProvider getConfigProvider() {
        return configProvider;
    }

    private Properties loadProperties() {
        InputStream inputStream = null;

        try {

            URL url = ResourceUtils.getURL(getResourceLocation());
            inputStream = url.openStream();
            Properties properties = new Properties();

            if (url.getPath().endsWith(".properties")) {
                properties.load(inputStream);
            } else if (url.getPath().endsWith(".xml")) {
                properties.loadFromXML(inputStream);
            } else {
                throw new IllegalArgumentException("不支持的文件[" + url
                        + "], FileSetariaConfig 只支持加载[*.properties, *.xml]的properties文件");
            }
            return properties;
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("未发现配置文件 [" + getResourceLocation() + "]", e);
        } catch (IOException e) {
            throw new IllegalStateException("读取配置文件 [" + getResourceLocation() + "]错误", e);
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

    private String getResourceLocation() {
        return getConfigParameter(SETARIA_CONFIG_LOCATION);
    }
}
