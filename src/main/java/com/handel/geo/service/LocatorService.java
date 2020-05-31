package com.handel.geo.service;

import com.handel.geo.model.Locator;
import com.handel.geo.repository.LocatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("locatorService")
public class LocatorService {

    @Autowired
    UserService userService;

    @Qualifier("locatorRepository")
    @Autowired
    private LocatorRepository locatorRepository;

    public Locator saveLocator(Locator locator){
        return locatorRepository.save(locator);
    }

    public List<Locator> getAllLocators() {
        return new ArrayList<>(locatorRepository.getAllLocators());
    }

    public void deleteLocator(Integer id) {
        System.out.println("ID lokalizatora "+id);
        locatorRepository.deleteLocatorById(id);
    }

    public Locator getLocator(Integer id) {
        return locatorRepository.getLocatorById(id);
    }

    public void updateLocator(Locator locator) {
        Locator updatedLocator = locator;
        updatedLocator.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        updatedLocator.setDate(LocalDateTime.now());
        locatorRepository.deleteLocatorById(locator.getId());
        locatorRepository.save(updatedLocator);
    }
}
