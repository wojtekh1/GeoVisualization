package com.handel.geo.controller;

import com.handel.geo.model.Locator;
import com.handel.geo.model.Users;
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
public class LocatorsController {

    @Autowired
    UserService userService;
    @Autowired
    LocatorService locatorService;

    @RequestMapping(value = "/locators", method = RequestMethod.GET)
    public ModelAndView locators() {
        Locator locator = new Locator();
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"));
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            locator.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        System.out.println(locator.getUser());
//        List<Locator> allLocator = locatorService.getAllLocators();
        List<Locator> allUserLocator = locatorService.getAllUserLocators(userService.findUserIdByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        ModelAndView modelAndView = new ModelAndView();
        Users user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("allUserLocators", allUserLocator);
        modelAndView.addObject("user", user);


        modelAndView.addObject("locator", locator);

        modelAndView.setViewName("locators");
        return modelAndView;
    }

    @RequestMapping(value = "/locators", method = RequestMethod.POST)
    public ModelAndView createLocator(@Valid Locator locator, BindingResult bindingResult, ModelAndView modelAndView) {
        System.out.println("LOKALIZATOR: --> " + locator);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            modelAndView.setViewName("locators");
        } else {
            if (locator != null) {
                locator.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                locator.setModyficationDate(LocalDateTime.now());
                locatorService.saveLocator(locator);
                modelAndView.setViewName("redirect:/locators");
            } else {
                modelAndView.setViewName("access-denied");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteLocator(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        Locator locator = locatorService.getLocator(Long.parseLong(id));
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locatorService.checkAccess(locator.getApiKey(),authName)) {
            locatorService.deleteLocator(Long.parseLong(id));
            modelAndView.setViewName("redirect:/locators");
        }else {
            modelAndView.setViewName("access-denied");
        };
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editLocator(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Locator locator = locatorService.getLocator(id);
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locatorService.checkAccess(locator.getApiKey(),authName)) {
            System.out.println("LOKALIZATOR ID z metody GET-------" + locator.getId());
            modelAndView.addObject("locator", locator);
            modelAndView.setViewName("editLocatorForm");
        }else {
            modelAndView.setViewName("access-denied");
        };
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editLocator(@Valid Locator locator, BindingResult bindingResult, ModelAndView modelAndView) {
        System.out.println("LOKALIZATOR ID z met POST"+locator.getId());
        locatorService.updateLocator(locator);

        modelAndView.setViewName("redirect:/locators");
        return modelAndView;
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public ModelAndView testLocator(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Locator locator = locatorService.getLocator(id);
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locatorService.checkAccess(locator.getApiKey(),authName)) {
            modelAndView.addObject("locator", locator);
            modelAndView.setViewName("testLocator");
        }else {
            modelAndView.setViewName("access-denied");
        };
        return modelAndView;
    }
}
