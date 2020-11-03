package com.handel.geo.controller;

import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("/allLocators")
    public List<Locator> getAllLocators(){
        return apiService.getAllLocators();
    }

    @GetMapping("/allUserLocators")
    public List<Locator> getAllLocators(@RequestBody String apiKey, HttpServletResponse response){
        try {
            return apiService.getAllUserLocatorsByApiKey(apiKey);
        }
        catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Key Not Found", exc);
        }
    }

    @GetMapping("/locatorLastLocation")
    public Location getLastLocation(@RequestBody String apiKey){
        return apiService.getLastLocatorLocation(apiKey);
    }

    @PostMapping("/postLocation")
    public Object postLocation(@RequestBody Location location){
//        System.out.println("metoda postLocation: "+ location);
        if(location.getDate_time()==null){
            return new ResponseEntity<String>("The location date must be set and can only be saved 24 hours after locating",HttpStatus.BAD_REQUEST);
        }if(location.getLambda()==null){
            return new ResponseEntity<String>("Lambda must be set",HttpStatus.BAD_REQUEST);
        }if(location.getFi()==null){
            return new ResponseEntity<String>("Fi must be set",HttpStatus.BAD_REQUEST);
        }
        Location location1 = apiService.postLocation(location);
//        System.out.println("metoda apiService.postLocation: "+ location1);
        if (location1.getLocator()==null) {
            return new ResponseEntity<String>("Api key not found",HttpStatus.UNAUTHORIZED);
        }if(Float.isNaN(location1.getFi())){
            return new ResponseEntity<String>("The Fi coordinate must be in the range from -90 to 90",HttpStatus.BAD_REQUEST);
        }if(Float.isNaN(location1.getLambda())){
            return new ResponseEntity<String>("The Lambda coordinate must be in the range from -180 to 180",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<String>("Location saved",HttpStatus.OK);
        }
    }
}
