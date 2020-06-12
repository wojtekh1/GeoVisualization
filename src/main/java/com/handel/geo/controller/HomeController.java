package com.handel.geo.controller;

import com.handel.geo.model.Users;
import com.handel.geo.model.Locator;
import com.handel.geo.service.LocatorService;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    LocatorService locatorService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        List<Locator> allLocator = locatorService.getAllLocators();
        ModelAndView modelAndView = new ModelAndView();
        Users user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("allLocators", allLocator);
        modelAndView.addObject("user", user);
        Locator locator = new Locator();
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"));
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            locator.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        modelAndView.addObject("locator", locator);
        System.out.println(locator.getUser());
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
