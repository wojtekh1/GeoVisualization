package com.handel.geo.controller;

import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.service.ApiService;
import com.handel.geo.service.LocationService;
import com.handel.geo.service.LocatorService;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Locator> getAllLocators(@RequestBody String apiKey){
    return apiService.getAllUserLocatorsByApiKey(apiKey);
    }

    @GetMapping("/locatorLastLocation")
    public Location getLastLocation(@RequestBody String apiKey){
        return apiService.getLastLocatorLocation(apiKey);
    }

//    @PostMapping("/postLocation")
//    public Location postLocation(@RequestBody String apiKey){
//        return apiService.postLocation(apiKey);
//    }
}
