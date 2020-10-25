package com.handel.geo.service;

import com.handel.geo.model.Locator;
import com.handel.geo.model.Users;
import com.handel.geo.repository.LocatorRepository;
import com.handel.geo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("locatorService")
public class LocatorService {

    @Qualifier("locatorRepository")
    @Autowired
    private LocatorRepository locatorRepository;

    @Qualifier("usersRepository")
    @Autowired
    private UsersRepository usersRepository;

    public Locator saveLocator(Locator locator){
        return locatorRepository.save(locator);
    }

    public List<Locator> getAllLocators() {
        return new ArrayList<>(locatorRepository.getAllLocators());
    }
    public List<Locator> getAllUserLocators(Integer userId) {
        return new ArrayList<Locator>(locatorRepository.getAllUserLocators(userId));
    }

    public void deleteLocator(String id) {
        System.out.println("ID lokalizatora "+id);
        locatorRepository.deleteLocatorById(id);
    }

    public Locator getLocator(Long id) {
        return locatorRepository.getLocatorById(id);
    }

    public void updateLocator(Locator locator) {
        locatorRepository.save(locator);
    }

    public List<Locator> getAllUserLocatorsByApiKey(String apiKey) {
        Users user= locatorRepository.getUserByApiKey(apiKey);
        return new ArrayList<Locator>(locatorRepository.getAllUserLocators(user.getUserId()));
    }
    public Locator getLocatorByApiKey(String apiKey) {
        return locatorRepository.getLocatorById(locatorRepository.getLocatorIdByApiKey(apiKey));
    }
}
