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

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.weghst.setaria.console.web.Constants;
import com.weghst.setaria.console.web.Result;
import com.weghst.setaria.console.web.vo.ResetPasswordVo;
import com.weghst.setaria.console.web.vo.UserVo;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.service.UserService;

/**
 * 用户控制器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable int id) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userService.findById(id), userVo);
        userVo.setAppIds(userService.findUserAppIds(id));
        return userVo;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        userService.save(user, userVo.getAppIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable int id, @RequestBody UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setId(id);

        userService.update(user, userVo.getAppIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable int id) {
        userService.deleteById(id);
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.PATCH)
    public Object updatePassword(@RequestBody ResetPasswordVo resetPasswordVo, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        userService.updatePassword(user.getId(), resetPasswordVo.getOldPassword(), resetPasswordVo.getNewPassword());
        return Result.SUCCESS;
    }

    @RequestMapping
    public Object findAll() {
        return userService.findAll();
    }

    @RequestMapping("/ordinaries")
    public Object findOrdinaryUsers() {
        return userService.findOrdinaryUsers();
    }

}
