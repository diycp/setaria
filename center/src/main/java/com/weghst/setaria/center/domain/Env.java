package com.weghst.setaria.center.domain;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public enum Env {

    /**
     *
     */
    production(1),
    /**
     *
     */
    test(2),
    /**
     *
     */
    developer(3);

    private final int code;

    Env(int code) {
        this.code = code;
    }

    /**
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code
     * @return
     */
    public static Env fromCode(int code) {
        for (Env e : Env.values()) {
            if (e.code == code) {
                return e;
            }
        }

        throw new IllegalArgumentException("未找到环境编码[code=" + code + "]");
    }
}
