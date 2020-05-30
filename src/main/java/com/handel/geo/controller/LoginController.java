package com.handel.geo.controller;


import com.handel.geo.model.Users;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(ModelAndView modelAndView) {
        modelAndView.addObject("users", new Users());
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Users user, BindingResult bindingResult, ModelAndView modelAndView) {

        if (userService.findUserByEmail(user.getEmail()) != null) {
            System.out.println("Email zarezerwowany");
            bindingResult
                    .rejectValue("email", "error.user",
                            "Email zarezerwowany");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("------------------bindingResult.hasErrors---------------");
            if(bindingResult.hasFieldErrors("password"))  modelAndView.addObject("errorPassword", "Hasło musi mieć przynajmniej 5 znaków");
            if(bindingResult.hasFieldErrors("email"))  modelAndView.addObject("errorEmail", "Wprowadź poprawny adres email");
            if(bindingResult.hasFieldErrors("roles"))  modelAndView.addObject("errorRoles", "Wybierz uprawnienia");

            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    });
            modelAndView.addObject("errorMessage", "Formularz wypełniony błędnie");
            modelAndView.addObject("users", new Users());
            modelAndView.setViewName("registration");
        } else {
            System.out.println("------------------zapis---------------");
            userService.saveNewUser(user);
            modelAndView.addObject("successMessage", "User has been registered!");
            modelAndView.addObject("users", new Users());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }




}
