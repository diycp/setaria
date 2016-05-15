/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.setaria.client;

/**
 * {@code SetariaConfig} 异常.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SetariaConfigException extends RuntimeException {

    /**
     * 通过错误详细信息与目标异常创建 {@code SetariaConfigException}.
     *
     * @param message 详细信息
     * @param cause   目标异常
     */
    public SetariaConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 通过目标异常创建 {@code SetariaConfigException}.
     *
     * @param cause 目标异常
     */
    public SetariaConfigException(Throwable cause) {
        super(cause);
    }
}
