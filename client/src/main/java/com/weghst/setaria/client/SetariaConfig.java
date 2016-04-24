package com.weghst.setaria.client;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface SetariaConfig {

    /**
     *
     */
    void init();

    /**
     *
     */
    void refresh();

    /**
     *
     */
    void destroy();

    /**
     * @return
     */
    ConfigProvider getConfigProvider();

    /**
     * @param listener
     */
    void addListener(SetariaConfigListener listener);

    /**
     * @param listener
     * @return
     */
    boolean removeListener(SetariaConfigListener listener);
}
