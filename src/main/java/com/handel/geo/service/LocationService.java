package com.handel.geo.service;

import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.repository.LocationsRepository;
import com.handel.geo.repository.LocatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("locationService")
public class LocationService {

//    @Autowired
//    UserService userService;

    @Qualifier("locationsRepository")
    @Autowired
    private LocationsRepository locationsRepository;

    @Qualifier("locatorRepository")
    @Autowired
    private LocatorRepository locatorRepository;

//    public Locator saveLocation(Locator locator){
//        return locationsRepository.save(locatoions);
//    }

    public List<Location> getAllLocations() {
        return new ArrayList<>(locationsRepository.getAllLocations());
    }
    public List<Location> getLastLocatorLocations(Integer userId) {
        List<String> allUserLocatorsId = locatorRepository.getAllUserLocatorsId(userId);
//        System.out.println("UserLocationsID wynik:_"+allUserLocatorsId);
        return new ArrayList<Location>(locationsRepository.getLastLocatorLocations(allUserLocatorsId));
    }
    public List<Location> getAllUserLocations(Integer userId) {
        List<String> allUserLocatorsId = locatorRepository.getAllUserLocatorsId(userId);
//        System.out.println("UserLocationsID wynik:_"+allUserLocatorsId);
        return new ArrayList<Location>(locationsRepository.getLocationsByUser(allUserLocatorsId));
    }
    public List<Location> getLocatorLocations(String id) {
        return new ArrayList<Location>(locationsRepository.getLocatorLocations(id));
    }
    public void deleteLocation(Integer id) {
        locationsRepository.deleteLocationById(id);
    }

    public Location getLocator(Integer id) {
        return locationsRepository.getLocationById(id);
    }

    public void updateLocator(Location location) {
        locationsRepository.save(location);
    }
}
