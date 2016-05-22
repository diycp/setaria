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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weghst.setaria.console.web.Constants;
import com.weghst.setaria.console.web.ErrorCodes;
import com.weghst.setaria.console.web.Result;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.service.AppService;
import com.weghst.setaria.core.service.PasswordNotMatchedException;
import com.weghst.setaria.core.service.UserNotFoundException;
import com.weghst.setaria.core.service.UserService;

/**
 * 管理平台控制器.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Controller
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard(HttpSession session) {
        ModelAndView mav = new ModelAndView("dashboard");
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        mav.addObject("apps", appService.findAppsByUserIdOrRole(user.getId()));
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody User user, HttpSession session) {
        try {
            user = userService.authenticate(user.getEmail(), user.getPassword());
            session.setAttribute(Constants.SESSION_USER_ATTR_NAME, user);
        } catch (UserNotFoundException e) {
            return new Result(ErrorCodes.E_10001);
        } catch (PasswordNotMatchedException e) {
            return new Result(ErrorCodes.E_10002);
        }
        return new Result(user);
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.SESSION_USER_ATTR_NAME);
        return "redirect:/login.html";
    }
}
