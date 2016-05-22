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
 * Restful 结果返回.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Result {

    /**
     * 默认的成功结果. 该对象只会返回一个状态码值为 {@code 0} 的结果, 表示操作成功.
     * 该对象调用函数 {@link Result#setData(Object)}, {@link Result#setReasonPhrase(String)} 来设置
     * 数据对象及原因.
     */
    public static final Result SUCCESS = new Result() {

        @Override
        public int getCode() {
            return 0;
        }

        @Override
        public void setData(Object data) {
            throw new UnsupportedOperationException("Not supported yet");
        }

        @Override
        public void setReasonPhrase(String reasonPhrase) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    };

    private int code;
    private String reasonPhrase;
    private Object data;

    /**
     * 默认构建返回结果对象.
     */
    public Result() {
    }

    /**
     * 通过状态与原因构建返回结果对象.
     *
     * @param code         状态码
     * @param reasonPhrase 原因
     */
    public Result(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * 通过 {@code ErrorCodes} 创建构建返回结果对象.
     *
     * @param errorCode 错误码
     */
    public Result(ErrorCodes errorCode) {
        this.code = errorCode.getCode();
        this.reasonPhrase = errorCode.getMessage();
    }

    /**
     * 通过数据对象构建返回结果.
     *
     * @param data 数据对象
     */
    public Result(Object data) {
        this.data = data;
    }

    /**
     * 状态码, 300 以下为成功的状态码值.
     *
     * @return 状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置状态码, 300 以下为成功的状态码值.
     *
     * @param code 状态码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回原因, 一般在操作或者程序错误的情况用于与客户端交流的属性.
     *
     * @return 原因
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * 设置原因, 一般在操作或者程序错误的情况下用于与客户端交流的属性.
     *
     * @param reasonPhrase 原因
     */
    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * 返回数据对象.
     *
     * @return 数据对象
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置数据对象.
     *
     * @param data 数据对象
     */
    public void setData(Object data) {
        this.data = data;
    }
}
