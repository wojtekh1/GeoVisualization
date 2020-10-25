package com.handel.geo.service;

import com.handel.geo.model.DateTimeRange;
import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.repository.LocationsRepository;
import com.handel.geo.repository.LocatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public List<Location> getLocatorLocations(Long id) {
        return new ArrayList<Location>(locationsRepository.getLocatorLocations(id));
    }
    public List<Location> getLocatorLocationsByDate(Long id, DateTimeRange range) {
        return new ArrayList<Location>(locationsRepository.getLocatorLocationsByDate(id,range.getDateTimeFrom(),range.getDateTimeTo()));
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


    public DateTimeRange setRange(String fromDateTime, String toDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
        LocalDateTime fromDate = LocalDateTime.parse(fromDateTime, formatter);
        LocalDateTime toDate = LocalDateTime.parse(toDateTime, formatter);
        DateTimeRange range = new DateTimeRange(fromDate,toDate);
        return range;
    }
}
