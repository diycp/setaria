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
package com.weghst.setaria.core.service;

/**
 * 未找到应用异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class AppNotFoundException extends RuntimeException {

    /**
     * 通过详细信息构建异常.
     *
     * @param message 详细信息
     */
    public AppNotFoundException(String message) {
        super(message);
    }
}
