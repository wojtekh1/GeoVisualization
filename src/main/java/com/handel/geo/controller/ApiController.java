package com.handel.geo.controller;

import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.service.ApiService;
import com.handel.geo.service.LocationService;
import com.handel.geo.service.LocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Kontroler API
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;
    @Autowired
    LocatorService locatorService;
    @Autowired
    LocationService locationService;

    @GetMapping("/allLocators")
    public List<Locator> getAllLocators(){
        return apiService.getAllLocators();
    }

    @GetMapping("/allUserLocators")
    public Object getAllLocators(@RequestBody String apiKey, HttpServletResponse response){
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locatorService.checkAccess(apiKey,authName)) {
            try {
                return apiService.getAllUserLocatorsByApiKey(apiKey);
            } catch (Exception exc) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Key Not Found", exc);
            }
        }else {
            return new ResponseEntity<String>("Access denied", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/locatorLastLocation")
    public Object getLastLocation(@RequestBody String apiKey){
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locatorService.checkAccess(apiKey,authName)){
            return apiService.getLastLocatorLocation(apiKey);
        }else {
            return new ResponseEntity<String>("Access denied", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/postLocation")
    public Object postLocation(@RequestBody Location location){
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (locationService.checkAccess(location,authName)){
    //        System.out.println("metoda postLocation: "+ location);
            if(location.getDate_time()==null){
                return new ResponseEntity<String>("The location date must be set",HttpStatus.BAD_REQUEST);
            }else if(location.getLambda()==null){
                return new ResponseEntity<String>("Lambda must be set",HttpStatus.BAD_REQUEST);
            }else if(location.getFi()==null){
                return new ResponseEntity<String>("Fi must be set",HttpStatus.BAD_REQUEST);
            }
            Location location1 = apiService.postLocation(location);
    //        System.out.println("metoda apiService.postLocation: "+ location1);
            if (location1.getLocator()==null) {
                return new ResponseEntity<String>("Api key not found",HttpStatus.NOT_FOUND);
            }else if(location1.getDate_time()==null) {
                return new ResponseEntity<String>("The location can be saved only 24 hours after locating", HttpStatus.BAD_REQUEST);
            }else if(Float.isNaN(location1.getFi())){
                return new ResponseEntity<String>("The Fi coordinate must be in the range from -90 to 90",HttpStatus.BAD_REQUEST);
            }else if(Float.isNaN(location1.getLambda())){
                return new ResponseEntity<String>("The Lambda coordinate must be in the range from -180 to 180",HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<String>("Location saved",HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<String>("Access denied", HttpStatus.FORBIDDEN);
        }
    }
}
