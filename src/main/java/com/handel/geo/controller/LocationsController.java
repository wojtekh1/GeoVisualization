package com.handel.geo.controller;

import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.model.Users;
import com.handel.geo.service.LocationService;
import com.handel.geo.service.LocatorService;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LocationsController {

    @Autowired
    UserService userService;
    @Autowired
    LocatorService locatorService;
    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    public ModelAndView showLocator(@PathVariable("id") String id) {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView();
        Locator locator = locatorService.getLocator(id);
        System.out.println("LOKALIZATOR ID z metody GET-------" + locator.getId());
        modelAndView.addObject("locator", locator);
        modelAndView.setViewName("location");
        List<Locator> allUserLocators;
        allUserLocators = locatorService.getAllUserLocators(userService.findUserIdByEmail(authName));
        Users user = userService.findUserByEmail(authName);
        List<Location> allLocatorLocations;
        allLocatorLocations = locationService.getLocatorLocations(id);
        modelAndView.addObject("allUserLocators", allUserLocators);
        modelAndView.addObject("allLocatorLocations", allLocatorLocations);
        modelAndView.addObject("user", user);
        if(!authName.equals("anonymousUser")) {
            locator.setUser(userService.findUserByEmail(authName));
        }
//        System.out.println(user);
//        System.out.println(userId);
//        System.out.println("Locators :"+allUserLocators);
//        System.out.println("Locations :"+allUserLocations);
        return modelAndView;
    }
}
