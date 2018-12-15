package com.world.ico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lsb on 2018/12/11.
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login() {

        return "redirect:/pages/index_cn.html";
    }
}
