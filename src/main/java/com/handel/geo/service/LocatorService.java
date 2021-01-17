package com.handel.geo.service;

import com.handel.geo.model.Locator;
import com.handel.geo.model.Users;
import com.handel.geo.repository.LocatorRepository;
import com.handel.geo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serwis lokalizatorów
 */
@Service("locatorService")
public class LocatorService {

    @Qualifier("locatorRepository")
    @Autowired
    private LocatorRepository locatorRepository;

    @Qualifier("usersRepository")
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LocatorService locatorService;
    @Autowired
    private LocationService locationService;

    /** Metoda zapisująca lokalizator */
    public Locator saveLocator(Locator locator){
        return locatorRepository.save(locator);
    }

    /** Metoda zwracająca listę wszystkich lokalizatorów */
    public List<Locator> getAllLocators() {
        return new ArrayList<>(locatorRepository.getAllLocators());
    }

    /** Metoda zwracająca listę wszystkich lokalizatorów użytkownika o podanym ID */
    public List<Locator> getAllUserLocators(Integer userId) {
        return new ArrayList<Locator>(locatorRepository.getAllUserLocators(userId));
    }

    /** Metoda usuwająca lokalizator */
    public void deleteLocator(Long id) {
        System.out.println("ID lokalizatora "+id);
        locationService.deleteAllLocatorLocation(id);
        locatorRepository.deleteLocatorById(id);
    }

    /** Metoda zwracająca lokalizator o podanym ID */
    public Locator getLocator(Long id) {
        return locatorRepository.getLocatorById(id);
    }

    /** Metoda aktualizująca lokalizator */
    public void updateLocator(Locator locator) {
        locatorRepository.save(locator);
    }

    /** Metoda zwracająca listę wszystkich lokalizatorów użytkownika którego jest lokalizator o podanym apiKey */
    public List<Locator> getAllUserLocatorsByApiKey(String apiKey) {
        Users user= locatorRepository.getUserByApiKey(apiKey);
        return new ArrayList<Locator>(locatorRepository.getAllUserLocators(user.getUserId()));
    }

    /** Metoda zwracająca lokalizator podanym apiKey */
    public Locator getLocatorByApiKey(String apiKey) {
        return locatorRepository.getLocatorById(locatorRepository.getLocatorIdByApiKey(apiKey));
    }

    /** Metoda sprawdzająca dostępność użytkownika do lokalizatora o podanym apiKey */
    public boolean checkAccess(String apiKey, String authName) {
        Locator locator=locatorService.getLocatorByApiKey(apiKey);
        if (locator.getUser().getEmail()==authName){
            return true;
        }else{
            return false;
        }
    }
}
