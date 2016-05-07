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
package com.weghst.setaria.console.web.vo;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import com.weghst.setaria.core.domain.Env;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class AppVo {

    private int id;
    private String name;
    private Env env;
    private String description;
    private int[] userIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getUserIds() {
        if (userIds == null) {
            return ArrayUtils.EMPTY_INT_ARRAY;
        }
        return userIds;
    }

    public void setUserIds(int[] userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "AppVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", env=" + env +
                ", description='" + description + '\'' +
                ", userIds=" + Arrays.toString(userIds) +
                '}';
    }
}
