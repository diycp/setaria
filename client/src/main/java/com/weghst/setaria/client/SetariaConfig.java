package com.weghst.setaria.client;

/**
 * 配置管理客户端接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface SetariaConfig {

    /**
     * 初始化配置信息.
     */
    void init();

    /**
     * 刷新配置信息.
     */
    void refresh();

    /**
     * 销毁配置信息.
     */
    void destroy();

    /**
     * 返回配置提供者接口.
     *
     * @return {@link ConfigProvider}
     */
    ConfigProvider getConfigProvider();

    /**
     * 添加配置监听器.
     *
     * @param listener 监听器
     */
    void addListener(SetariaConfigListener listener);

    /**
     * 删除配置监听器. 返回删除成功或失败.
     *
     * @param listener 监听器
     * @return true/false
     */
    boolean removeListener(SetariaConfigListener listener);
}
