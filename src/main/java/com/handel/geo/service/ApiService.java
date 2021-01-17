package com.handel.geo.service;


import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Serwis API
 */
@Service("apiService")
public class ApiService {
    
    @Autowired
    UserService userService;
    @Autowired
    LocatorService locatorService;
    @Autowired
    LocationService locationService;

    /** Metoda zwracająca listę wszystkich lokalizatorów */
    public List<Locator> getAllLocators() {
        return locatorService.getAllLocators();
    }

    /** Metoda zwracająca listę wszystkich lokalizatorów użytkownika dla podanego klucza API*/
    public List<Locator> getAllUserLocatorsByApiKey(String apiKey) {
        return locatorService.getAllUserLocatorsByApiKey(apiKey);
    }

    /** Metoda zwracająca ostatnią lokalizację dla lokalizatora o podanym kluczu API*/
    public Location getLastLocatorLocation(String apiKey) {
        Locator locator = locatorService.getLocatorByApiKey(apiKey);
        return locationService.getLastLocatorLocations(locator.getUser().getUserId()).get(0);
    }

    /** Metoda zapisująca lokalizację */
    public Location postLocation(@RequestBody  Location location) {
        Locator locator=locatorService.getLocatorByApiKey(location.getLocator().getApiKey());
        Location locationNew = new Location(
                location.getFi(),
                location.getLambda(),
                location.getH(),
                location.getAccuracy(),
                location.getDate_time(),
                locator);
        return locationService.saveLocation(locationNew);
    }



}