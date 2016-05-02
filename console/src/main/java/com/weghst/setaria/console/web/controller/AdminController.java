package com.weghst.setaria.console.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weghst.setaria.console.web.Constants;
import com.weghst.setaria.console.web.ErrorCodes;
import com.weghst.setaria.console.web.ErrorResult;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.service.AppService;
import com.weghst.setaria.core.service.PasswordNotMatchedException;
import com.weghst.setaria.core.service.UserNotFoundException;
import com.weghst.setaria.core.service.UserService;

/**
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

    @RequestMapping(value = "login.v", method = RequestMethod.GET)
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody User user, HttpSession session) {
        try {
            user = userService.authenticate(user.getEmail(), user.getPassword());
            session.setAttribute(Constants.SESSION_USER_ATTR_NAME, user);
        } catch (UserNotFoundException e) {
            ErrorResult result = new ErrorResult(ErrorCodes.E_10001);
            return ResponseEntity.badRequest().body(result);
        } catch (PasswordNotMatchedException e) {
            ErrorResult result = new ErrorResult(ErrorCodes.E_10002);
            return ResponseEntity.badRequest().body(result);
        }
        return "{}";
    }

    @RequestMapping
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.SESSION_USER_ATTR_NAME);
        return "redirect:login.v";
    }
}
