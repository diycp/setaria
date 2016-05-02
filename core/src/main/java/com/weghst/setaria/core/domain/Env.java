package com.weghst.setaria.core.domain;

/**
 * 应用环境枚举类型.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public enum Env {

    /**
     * 生产环境.
     */
    production(1),
    /**
     * 测试环境.
     */
    test(2),
    /**
     * 开发环境.
     */
    developer(3);

    private final int code;

    Env(int code) {
        this.code = code;
    }

    /**
     * 应用环境数字编码.
     *
     * @return 数字编码
     */
    public int getCode() {
        return code;
    }

    /**
     * 转换数字编码为枚举类型.
     *
     * @param code 数字编码
     * @return 枚举类型
     * @throws IllegalArgumentException 如果编码不存在对应的枚举
     */
    public static Env fromCode(int code) throws IllegalArgumentException {
        for (Env e : Env.values()) {
            if (e.code == code) {
                return e;
            }
        }

        throw new IllegalArgumentException("未找到环境编码[code=" + code + "]");
    }
}
