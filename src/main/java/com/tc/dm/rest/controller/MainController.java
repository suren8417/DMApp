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
    public String dashBoard() {
        return "dashBoard";
    }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
    return "login";
}

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/addNewItem", method = RequestMethod.GET)
    public String addNewItem() {
        return "addNewItem";
    }

    @RequestMapping(value = "/manageCollection", method = RequestMethod.GET)
    public String collection() {
        return "collection";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "user";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public String validate() {
        return "validate";
    }

    @RequestMapping(value = "/recentItem", method = RequestMethod.GET)
    public String recentItem() {
        return "recentItem";
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
    public String deleteItem() {
        return "deleteItem";
    }

}
