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
package com.weghst.setaria.console.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.weghst.setaria.console.web.Constants;
import com.weghst.setaria.console.web.ErrorCodes;
import com.weghst.setaria.console.web.Result;
import com.weghst.setaria.core.ObjectMapperUtils;
import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.Config;
import com.weghst.setaria.core.domain.Env;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.dto.ConfigDto;
import com.weghst.setaria.core.service.AppNotFoundException;
import com.weghst.setaria.core.service.AppService;
import com.weghst.setaria.core.service.ConfigService;

/**
 * 配置控制器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Controller
@RequestMapping("/configs")
public class ConfigController {

    @Autowired
    private AppService appService;
    @Autowired
    private ConfigService configService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody Config config, HttpSession session) {
        try {
            User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
            configService.save(config, user.getEmail());
            return Result.SUCCESS;
        } catch (DuplicateKeyException e) {
            Result result = new Result(2000, "重复的键 [" + config.getKey() + "]");
            return result;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@PathVariable int id, @RequestBody Config config, HttpSession session) {
        try {
            User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
            config.setId(id);
            configService.update(config, user.getEmail());
            return Result.SUCCESS;
        } catch (DuplicateKeyException e) {
            Result result = new Result(2000, "重复的键 [" + config.getKey() + "]");
            return result;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        configService.delete(id, user.getEmail());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable int id) {
        return new Result(configService.findById(id));
    }

    @RequestMapping(value = "/app/{appId}", method = RequestMethod.GET)
    @ResponseBody
    public Object findByAppId(@PathVariable int appId) {
        return configService.findByAppId(appId);
    }

    /**
     * @param appName
     * @param appEnv
     * @return
     */
    @RequestMapping(value = "/pull/{appName}/{appEnv}", method = RequestMethod.GET)
    @ResponseBody
    public Result pull(@PathVariable String appName, @PathVariable String appEnv) {
        Env env;
        try {
            env = Env.valueOf(appEnv);
        } catch (IllegalArgumentException e) {
            Result result = new Result(ErrorCodes.E_20001);
            result.setReasonPhrase("错误的应用环境 [" + appEnv + "], 环境可选值为 " + Arrays.toString(Env.values()));
            return result;
        }

        try {
            return new Result(configService.findByAppNameAndEnv(appName, env));
        } catch (AppNotFoundException e) {
            Result result = new Result(ErrorCodes.E_20002);
            result.setReasonPhrase(e.getMessage());
            return result;
        }
    }

    @RequestMapping(value = "/import/{appId}")
    @ResponseBody
    public Object importConfig(@PathVariable int appId, @RequestParam("file") MultipartFile multipartFile,
                               HttpSession session) {
        try {
            Config[] configs = ObjectMapperUtils.readValue(multipartFile.getInputStream(), Config[].class);
            for (Config config : configs) {
                config.setAppId(appId);
            }

            User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
            configService.saveOrUpdate(configs, user.getEmail());
            return Result.SUCCESS;
        } catch (Exception e) {
            Result errorResult = new Result();
            errorResult.setCode(ErrorCodes.UNKNOWN.getCode());
            errorResult.setReasonPhrase("上传文件失败 ->> " + e.getMessage());
            return errorResult;
        }
    }

    @RequestMapping(value = "/export/{appId}", method = RequestMethod.GET)
    public void exportConfig(@PathVariable int appId, HttpServletResponse response) throws IOException {
        List<Config> list = configService.findByAppId(appId);
        List<ConfigDto> configDtos = new ArrayList<>(list.size());
        for (Config c : list) {
            ConfigDto configDto = new ConfigDto();
            BeanUtils.copyProperties(c, configDto);
            configDtos.add(configDto);
        }

        App app = appService.findById(appId);
        String filename = app.getName() + "-" + app.getEnv() + "-" + app.getLastUpdatedTime() + ".json";
        byte[] bytes = ObjectMapperUtils.writeValueAsBytes(configDtos);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        response.setContentLength(bytes.length);

        response.getOutputStream().write(bytes);
    }
}
