package com.weghst.setaria.console.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.weghst.setaria.console.web.Constants;
import com.weghst.setaria.console.web.Result;
import com.weghst.setaria.console.web.vo.AppVo;
import com.weghst.setaria.core.domain.App;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.service.AppService;
import com.weghst.setaria.core.service.UserService;

/**
 * 应用控制器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Controller
@RequestMapping("/apps")
public class AppController {

    @Autowired
    private AppService appService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody AppVo appVo) {
        App app = new App();
        BeanUtils.copyProperties(appVo, app);

        appService.save(app, appVo.getUserIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@PathVariable int id, @RequestBody AppVo appVo) {
        App app = new App();
        BeanUtils.copyProperties(appVo, app);
        app.setId(id);

        appService.update(app, appVo.getUserIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        appService.deleteById(id, user.getEmail());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable int id) {
        App app = appService.findById(id);
        AppVo appVo = new AppVo();
        BeanUtils.copyProperties(app, appVo);

        appVo.setUserIds(appService.findAppUserIds(id));
        return appVo;
    }

    @RequestMapping(value = "/{id}/clientInfos", method = RequestMethod.GET)
    @ResponseBody
    public Object loadClientInfos(@PathVariable int id) {
        return appService.loadClientInfo(id);
    }

    @RequestMapping("/add.v")
    public ModelAndView addView() {
        ModelAndView mav = new ModelAndView("app-add");
        mav.addObject("users", userService.findOrdinaryUsers());
        return mav;
    }

    @RequestMapping("/edit.v")
    public ModelAndView editView() {
        ModelAndView mav = new ModelAndView("app-edit");
        mav.addObject("users", userService.findOrdinaryUsers());
        return mav;
    }

    @RequestMapping("/list.v")
    public ModelAndView listView(HttpSession session) {
        ModelAndView mav = new ModelAndView("app-list");

        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        mav.addObject("apps", appService.findAppsByUserIdOrRole(user.getId()));
        return mav;
    }

}
