package com.weghst.setaria.client;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class SetariaConfigContext {

    private static SetariaConfig setariaConfig;

    private SetariaConfigContext() {
    }

    /**
     * @return
     */
    public static SetariaConfig getSetariaConfig() {
        if (setariaConfig == null) {
            throw new IllegalStateException("未找到可用的 SetariaConfig 对象, 请确保 SetariaConfig 已初始化并通过" +
                    " SetariaConfigContext.setSetariaConfig(obj) 更新至上下文中");
        }
        return setariaConfig;
    }

    /**
     * @param setariaConfig
     */
    public static void setSetariaConfig(SetariaConfig setariaConfig) {
        SetariaConfigContext.setariaConfig = setariaConfig;
    }

}
