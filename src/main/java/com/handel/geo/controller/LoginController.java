package com.handel.geo.controller;


import com.handel.geo.model.Role;
import com.handel.geo.model.Users;
import com.handel.geo.service.RoleService;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Kontroler stron logowania oraz rejestracji
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(ModelAndView modelAndView) {
        Users user =new Users();
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        Users userAuth = userService.findUserByEmail(authName);
        List<Role> role = roleService.getUserRole();
        System.out.println("userAuth - "+userAuth);
        System.out.println("userAuth role - "+role);
        if (authName.equals("anonymousUser")) {
            modelAndView.addObject("availableRole", roleService.getUserRole());
            user.setRoles(role);
        }else {
            if (userAuth.getRoles().contains(roleService.findRole("ADMIN"))) {
                modelAndView.addObject("availableRole", roleService.getAllRoles());
            }else{
                modelAndView.addObject("availableRole", roleService.getUserRole());
                user.setRoles(role);
            }
        }
        modelAndView.addObject("users", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Users user, BindingResult bindingResult, ModelAndView modelAndView) {
        System.out.println(user);
        System.out.println(bindingResult);
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
            String authName = SecurityContextHolder.getContext().getAuthentication().getName();
            Users userAuth = userService.findUserByEmail(authName);
            if (authName.equals("anonymousUser")) {
                modelAndView.addObject("availableRole", roleService.getUserRole());
            }else {
                if (userAuth.getRoles().contains(roleService.findRole("ADMIN"))) {
                    modelAndView.addObject("availableRole", roleService.getAllRoles());
                }else{
                    modelAndView.addObject("availableRole", roleService.getUserRole());
                }
            }
            modelAndView.setViewName("registration");
        } else {
            System.out.println("------------------zapis---------------");
            userService.saveNewUser(user);
            modelAndView.addObject("successMessage", "Użytkownik został zarejestrowany!");

            Users userNew =new Users();
            String authName = SecurityContextHolder.getContext().getAuthentication().getName();
            Users userAuth = userService.findUserByEmail(authName);
            List<Role> role = roleService.getUserRole();
            System.out.println("userAuth - "+userAuth);
            System.out.println("userAuth role - "+role);
            if (authName.equals("anonymousUser")) {
                modelAndView.addObject("availableRole", roleService.getUserRole());
                userNew.setRoles(role);
            }else {
                if (userAuth.getRoles().contains(roleService.findRole("ADMIN"))) {
                    modelAndView.addObject("availableRole", roleService.getAllRoles());
                }else{
                    modelAndView.addObject("availableRole", roleService.getUserRole());
                    userNew.setRoles(role);
                }
            }
            modelAndView.addObject("users", userNew);
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }




}
