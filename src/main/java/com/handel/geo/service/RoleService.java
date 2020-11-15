package com.handel.geo.service;

import com.handel.geo.model.Role;
import com.handel.geo.model.Users;
import com.handel.geo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

//    @Autowired
//    UserService userService;
//    @Autowired
//    LocatorService locatorService;
//    @Autowired
//    LocationService locationService;
    @Qualifier("roleRepository")
    @Autowired
    RoleRepository roleRepository;
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillTestData() {
        saveRole(new Role(1,"ADMIN"));
        saveRole(new Role(2,"USER"));
    }
    public List<Role> getAllRoles(){
        return roleRepository.findAllRoles();
    }
    public List<Role> getUserRole() {
        return roleRepository.findUserRole();
    }

    public Role findRole(String role) {
        return roleRepository.findRole(role);
    }
//    INSERT INTO ROLE VALUES (1,'ADMIN');
//    INSERT INTO ROLE VALUES (2,'USER');
}
