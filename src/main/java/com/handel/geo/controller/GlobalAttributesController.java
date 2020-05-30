package com.handel.geo.controller;


import com.handel.geo.model.Role;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SessionAttributes({"allItems", "allCategories", "allPrios", "states"})
@ControllerAdvice
public class GlobalAttributesController {


    @ModelAttribute("roles")
    public List<Role> getRoles() {
       return Stream.of(new Role(1, "ADMIN "), new Role(2, "USER"))
                .collect(Collectors.toList());
    }
    @ModelAttribute("role")
    public List<Role> getRole() {
        return Stream.of(new Role(2, "USER"))
                .collect(Collectors.toList());
    }

}
