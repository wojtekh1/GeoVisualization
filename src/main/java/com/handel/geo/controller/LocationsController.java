package com.handel.geo.controller;

import com.handel.geo.model.DateTimeRange;
import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.model.Users;
import com.handel.geo.service.LocationService;
import com.handel.geo.service.LocatorService;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public ModelAndView showLocator(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Locator locator = locatorService.getLocator(id);
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locatorService.checkAccess(locator.getApiKey(),authName)) {
            System.out.println("LOKALIZATOR ID z metody GET-------" + locator.getId());
            modelAndView.addObject("locator", locator);
            modelAndView.setViewName("location");
            List<Locator> allUserLocators;
            allUserLocators = locatorService.getAllUserLocators(userService.findUserIdByEmail(authName));
            Users user = userService.findUserByEmail(authName);
            List<Location> allLocatorLocations;
            allLocatorLocations = locationService.getLocatorLocations(id);
            if (allLocatorLocations.size()==0) {
                modelAndView.setViewName("redirect:/");
                return modelAndView;
            }
            modelAndView.addObject("allUserLocators", allUserLocators);
            modelAndView.addObject("allLocatorLocations", allLocatorLocations);
            modelAndView.addObject("user", user);
            if (!authName.equals("anonymousUser")) {
                locator.setUser(userService.findUserByEmail(authName));
            }
            DateTimeRange range = new DateTimeRange();
            range.setDateTimeFrom(allLocatorLocations.get(0).getDate_time());
            modelAndView.addObject("range", range);
        }else {
            modelAndView.setViewName("access-denied");
        };
        return modelAndView;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    @RequestMapping(value = "/location/{id}/{from}/{to}", method = RequestMethod.GET)
    public ModelAndView showLocatorBetween(@PathVariable("id") Long id, @PathVariable("from") String fromDateTime, @PathVariable("to") String toDateTime) {
        ModelAndView modelAndView = new ModelAndView();
        Locator locator = locatorService.getLocator(id);
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locatorService.checkAccess(locator.getApiKey(),authName)) {
            Users user = userService.findUserByEmail(authName);
            List<Locator> allUserLocators;
            allUserLocators = locatorService.getAllUserLocators(userService.findUserIdByEmail(authName));
            List<Location> allLocatorLocationsByDate;
            DateTimeRange range = locationService.setRange(fromDateTime, toDateTime);
            modelAndView.addObject("range", range);
            allLocatorLocationsByDate = locationService.getLocatorLocationsByDate(id, range);
            modelAndView.addObject("locator", locator);
            modelAndView.addObject("allUserLocators", allUserLocators);
            modelAndView.addObject("allLocatorLocations", allLocatorLocationsByDate);
            modelAndView.addObject("user", user);
            if (!authName.equals("anonymousUser")) {
                locator.setUser(userService.findUserByEmail(authName));
            }
            modelAndView.setViewName("location");
        }else {
            modelAndView.setViewName("access-denied");
        };
        return modelAndView;
    }
}
