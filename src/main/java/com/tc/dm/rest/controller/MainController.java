package com.tc.dm.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sg40304 on 7/15/15.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String test() {
        return "dashBoard";
    }
}
