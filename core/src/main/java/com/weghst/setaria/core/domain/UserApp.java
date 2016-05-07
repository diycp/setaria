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
package com.weghst.setaria.core.domain;

import java.io.Serializable;

/**
 * 用户应用映射实体.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserApp implements Serializable {

    private static final long serialVersionUID = 1515460880439336865L;

    /**
     * 主键.
     */
    private int id;
    /**
     * 用户 ID.
     */
    private int userId;
    /**
     * 应用 ID.
     */
    private int appId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "id=" + id +
                ", userId=" + userId +
                ", appId=" + appId +
                '}';
    }
}
