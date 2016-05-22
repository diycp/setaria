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
package com.weghst.setaria.client;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;
import org.testng.annotations.DataProvider;

import com.weghst.setaria.client.util.ObjectMapperUtils;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SetariaDataProvider {

    @DataProvider
    public static Object[][] setariaBean() {
        return new Object[][]{{getSetariaBean()}};
    }

    /**
     * @return
     */
    public static SetariaBean getSetariaBean() {
        try {
            File file = ResourceUtils.getFile("classpath:setaria.json");
            return ObjectMapperUtils.readValue(file, SetariaBean.class);
        } catch (FileNotFoundException e) {
            throw new SetariaConfigException(e);
        }
    }
}
