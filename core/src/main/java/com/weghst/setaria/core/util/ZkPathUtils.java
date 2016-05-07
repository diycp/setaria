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
package com.weghst.setaria.core.util;

/**
 * Zookeeper Path 工具类.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class ZkPathUtils {

    /**
     * 连接目录地址并返回 Zookeeper Path.
     *
     * @param segments 目录数组
     * @return Zookeeper Path
     * @throws IllegalArgumentException 如果目录数组长度为 0 , 目录元素为 null 或者空字符串
     */
    public static String join(String... segments) throws IllegalArgumentException {
        if (segments.length == 0) {
            throw new IllegalArgumentException("ZooKeeper 目录长度不能为 0");
        }

        char p = '/';
        StringBuilder sb = new StringBuilder();
        sb.append(p);
        for (String s : segments) {
            if (s == null || s.isEmpty()) {
                throw new IllegalArgumentException("ZooKeeper 目录名不能为 null 或者长度为 0 的字符串");
            }
            for (char c : s.toCharArray()) {
                if (p == '/' && c == '/') {
                    continue;
                }
                sb.append(c);
                p = c;
            }
            p = '/';
            sb.append(p);
        }
        return sb.substring(0, sb.length() - 1);
    }
}
