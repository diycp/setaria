package com.weghst.setaria.console.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Controller
@RequestMapping("/admin")
@EnableWebMvc
public class AdminController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("dashboard");
        return mav;
    }
}
