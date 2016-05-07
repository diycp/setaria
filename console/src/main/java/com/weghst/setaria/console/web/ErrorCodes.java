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
package com.weghst.setaria.console.web;

/**
 * 错码码定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public enum ErrorCodes {

    UNKNOWN(-1, "未知错误"),
    E_10001(10001, "用户不存在"),
    E_10002(10002, "密码错误"),
    // configs pull 错误码
    E_20001(20001, "错误的应用环境"),
    E_20002(20002, "未找到应用")
    //
    ;

    private final int code;
    private final String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回错误码.
     *
     * @return 错误码
     */
    public int getCode() {
        return code;
    }

    /**
     * 返回错误描述.
     *
     * @return 错误描述
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCodes{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
