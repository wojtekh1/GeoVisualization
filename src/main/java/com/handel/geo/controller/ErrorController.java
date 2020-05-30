package com.handel.geo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "access-denied.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
