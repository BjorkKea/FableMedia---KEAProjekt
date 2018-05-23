package com.springsecurity.mysql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MysqlController {



    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/userHome")
    public String user(){
        return "userHome";
    }

    @RequestMapping(value="/")
    public String admin(){
        return "adminHome";
    }


}

