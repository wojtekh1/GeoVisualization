package com.handel.geo.controller;

import com.handel.geo.model.Location;
import com.handel.geo.model.Users;
import com.handel.geo.model.Locator;
import com.handel.geo.service.LocationService;
import com.handel.geo.service.LocatorService;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Kontroler strony głównej
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    LocatorService locatorService;
    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        Locator locator = new Locator();
        System.out.println(authName);
        System.out.println(locator.getUser());
        List<Locator> allUserLocators;
        allUserLocators = locatorService.getAllUserLocators(userService.findUserIdByEmail(authName));
        List<Location> allUserLocations;
        List<Location> lastLocatorsLocations;
        Integer userId = userService.findUserIdByEmail(authName);
        allUserLocations = locationService.getAllUserLocations(userId);
        lastLocatorsLocations = locationService.getLastLocatorLocations(userId);
        ModelAndView modelAndView = new ModelAndView();
        Users user = userService.findUserByEmail(authName);
        modelAndView.addObject("allUserLocators", allUserLocators);
        modelAndView.addObject("allUserLocations", allUserLocations);
        modelAndView.addObject("lastLocatorsLocations", lastLocatorsLocations);
        modelAndView.addObject("user", user);
        if(!authName.equals("anonymousUser")) {
            locator.setUser(userService.findUserByEmail(authName));
        }
        modelAndView.addObject("locator", locator);

        modelAndView.setViewName("home");
        System.out.println(user);
        System.out.println(userId);
        System.out.println("Locators :"+allUserLocators);
        System.out.println("Locations :"+allUserLocations);
        return modelAndView;
    }
}
