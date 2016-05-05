package com.weghst.setaria.console.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.weghst.setaria.console.web.Constants;
import com.weghst.setaria.console.web.Result;
import com.weghst.setaria.console.web.vo.ResetPasswordVo;
import com.weghst.setaria.console.web.vo.UserVo;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.service.AppService;
import com.weghst.setaria.core.service.UserService;

/**
 * 用户控制器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable int id) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userService.findById(id), userVo);
        userVo.setAppIds(userService.findUserAppIds(id));
        return userVo;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        userService.save(user, userVo.getAppIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Object update(@PathVariable int id, @RequestBody UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setId(id);

        userService.update(user, userVo.getAppIds());
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable int id) {
        userService.deleteById(id);
        return Result.SUCCESS;
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.PATCH)
    @ResponseBody
    public Object updatePassword(@RequestBody ResetPasswordVo resetPasswordVo, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        userService.updatePassword(user.getId(), resetPasswordVo.getOldPassword(), resetPasswordVo.getNewPassword());
        return Result.SUCCESS;
    }

    @RequestMapping
    @ResponseBody
    public Object findAll() {
        return userService.findAll();
    }

    @RequestMapping("/add.v")
    public ModelAndView addView(HttpSession session) {
        ModelAndView mav = new ModelAndView("user-add");
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        mav.addObject("apps", appService.findAppsByUserIdOrRole(user.getId()));
        return mav;
    }

    @RequestMapping("/edit.v")
    public ModelAndView editView(HttpSession session) {
        ModelAndView mav = new ModelAndView("user-edit");
        User user = (User) session.getAttribute(Constants.SESSION_USER_ATTR_NAME);
        mav.addObject("apps", appService.findAppsByUserIdOrRole(user.getId()));
        return mav;
    }

    @RequestMapping("/list.v")
    public ModelAndView listView() {
        ModelAndView mav = new ModelAndView("user-list");
        mav.addObject("users", userService.findAll());
        return mav;
    }

}
