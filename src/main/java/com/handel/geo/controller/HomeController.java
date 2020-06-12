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
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    LocatorService locatorService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        Locator locator = new Locator();
        System.out.println(authName.equals("anonymousUser"));
        System.out.println(authName);
        System.out.println(locator.getUser());
//        if(!authName.equals("anonymousUser")) {
            List<Locator> allUserLocators;
            allUserLocators = locatorService.getAllUserLocators(userService.findUserIdByEmail(authName));
//        }else
//        {
//            List<Locator> allUserLocators=new ArrayList<>
//        }
        ModelAndView modelAndView = new ModelAndView();
        Users user = userService.findUserByEmail(authName);
        modelAndView.addObject("allUserLocators", allUserLocators);
        modelAndView.addObject("user", user);
        if(!authName.equals("anonymousUser")) {
            locator.setUser(userService.findUserByEmail(authName));
        }
        modelAndView.addObject("locator", locator);

        modelAndView.setViewName("home");
        return modelAndView;
    }
}
