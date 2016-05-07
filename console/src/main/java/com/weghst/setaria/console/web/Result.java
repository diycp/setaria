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
     * 成功结果.
     */
    public static final Result SUCCESS = new Result() {

        @Override
        public Object getData() {
            return "success";
        }

        @Override
        public void setData(Object data) {
            throw new IllegalStateException("不允许修改 [SUCCESS] 属性值");
        }
    };

    private Object data;

    public Result() {
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
