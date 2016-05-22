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

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.weghst.setaria.console.web.Constants;
import com.weghst.setaria.console.web.Result;
import com.weghst.setaria.console.web.vo.AppVo;
import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.service.AppService;

/**
 * 应用控制器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@RestController
@RequestMapping("/apps")
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody AppVo appVo) {
        App app = new App();
        BeanUtils.copyProperties(appVo, app);

        appService.save(app, appVo.getUserIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable int id, @RequestBody AppVo appVo) {
        App app = new App();
        BeanUtils.copyProperties(appVo, app);
        app.setId(id);

        appService.update(app, appVo.getUserIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        appService.deleteById(id, user.getEmail());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable int id) {
        App app = appService.findById(id);
        AppVo appVo = new AppVo();
        BeanUtils.copyProperties(app, appVo);

        appVo.setUserIds(appService.findAppUserIds(id));
        return appVo;
    }

    @RequestMapping(value = "/myapps", method = RequestMethod.GET)
    public Object findAll(HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        List<App> myApps = appService.findAppsByUserIdOrRole(user.getId());
        return new Result(myApps);
    }

    @RequestMapping(value = "/{id}/clientInfos", method = RequestMethod.GET)
    public Result loadClientInfos(@PathVariable int id) {
        return new Result(appService.loadClientInfo(id));
    }

}
