package com.weghst.setaria.console.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 视图控制器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Controller
public class ViewController {

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView renderView(String v) {
        return new ModelAndView(v);
    }
}
