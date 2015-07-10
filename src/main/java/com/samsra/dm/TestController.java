package com.samsra.dm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "view";
    }
}
