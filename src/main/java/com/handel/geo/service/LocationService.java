package com.handel.geo.service;

import com.handel.geo.model.DateTimeRange;
import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import com.handel.geo.repository.LocationsRepository;
import com.handel.geo.repository.LocatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    @Autowired
    private LocatorService locatorService;

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

    public void deleteAllLocatorLocation(Long id) {
        Locator locator = locatorService.getLocator(id);
        locationsRepository.deleteAllLocatorLocation(locator); 
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
    public Location saveLocation(Location location){
        long between = ChronoUnit.MINUTES.between(LocalDateTime.now(),location.getDate_time());
        if(0<between || between<-(24*60)){
            location.setDate_time(null);
            return location;
        }if (location.getFi()<-90 || location.getFi()>90){
            location.setFi(Float.NaN);
            return location;
        }if(location.getLambda() < -180 || location.getLambda()>180){
            location.setLambda(Float.NaN);
            return location;
        }else {
            return locationsRepository.save(location);
        }
    }
    public boolean checkAccess(Location location, String authName) {
        Locator locator=locatorService.getLocatorByApiKey(location.getLocator().getApiKey());
        if (locator.getUser().getEmail()==authName){
            return true;
        }else{
            return false;
        }
    }




//    @EventListener(ApplicationReadyEvent.class)
//    public void fillTestData() {
//        saveLocation(new Location(52.162523f,21.044935f,130,0,LocalDateTime.of(2020, 10,14,12,18,06,82),locatorRepository.getLocatorById(1L)));
//        saveLocation(new Location(52.161813f,21.043848f,131,0,LocalDateTime.of(2020, 10,14,12,19,06,82),locatorRepository.getLocatorById(1L)));
//        saveLocation(new Location(52.159648f,21.045994f,129,0,LocalDateTime.of(2020, 10,14,12,20,06,82),locatorRepository.getLocatorById(1L)));
//        saveLocation(new Location(52.182523f,21.034935f,130,0,LocalDateTime.of(2020, 10,14,12,18,06,82),locatorRepository.getLocatorById(2L)));
//        saveLocation(new Location(52.181813f,21.033848f,131,0,LocalDateTime.of(2020, 10,14,12,19,06,82),locatorRepository.getLocatorById(2L)));
//        saveLocation(new Location(52.169648f,21.035994f,129,0,LocalDateTime.of(2020, 10,14,12,20,06,82),locatorRepository.getLocatorById(2L)));
//    }
}