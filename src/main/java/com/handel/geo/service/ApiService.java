package com.handel.geo.service;


import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service("apiService")
public class ApiService {
    
    @Autowired
    UserService userService;
    @Autowired
    LocatorService locatorService;
    @Autowired
    LocationService locationService;

    public List<Locator> getAllLocators() {
        return locatorService.getAllLocators();
    }

    public List<Locator> getAllUserLocatorsByApiKey(String apiKey) {
        return locatorService.getAllUserLocatorsByApiKey(apiKey);
    }

    public Location getLastLocatorLocation(String apiKey) {
        Locator locator = locatorService.getLocatorByApiKey(apiKey);
        return locationService.getLastLocatorLocations(locator.getUser().getUserId()).get(0);
    }


//    public Location postLocation(String apiKey) {
//    }
}