package com.test.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



/**
 * Controller返回数据
 */
@RestController
public class UserController {



    /**
     * 进入直播首页
     * @return
     */
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * 进入观众首页
     * @return
     */
    @GetMapping("live")
    public ModelAndView live() {
        return new ModelAndView("live");
    }


}
