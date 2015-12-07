package com.tc.dm.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sg40304 on 7/15/15.
 */
@Controller
public class MainController {


    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String test() {
        return "dashBoard";
    }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String test31() {
    return "login";
}

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String test1() {
        return "search";
    }

    @RequestMapping(value = "/addNewItem", method = RequestMethod.GET)
    public String test2() {
        return "addNewItem";
    }

    @RequestMapping(value = "/manageCollection", method = RequestMethod.GET)
    public String test3() {
        return "collection";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String test4() {
        return "user";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public String test5() {
        return "validate";
    }
}
