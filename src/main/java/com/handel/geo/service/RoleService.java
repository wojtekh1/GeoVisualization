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

/**
 * Serwis ról
 */
@Service
public class RoleService {

    @Qualifier("roleRepository")
    @Autowired
    RoleRepository roleRepository;
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    /** Metoda uzupełniająca role początkowe */
    @EventListener(ApplicationReadyEvent.class)
    public void fillTestData() {
        //    INSERT INTO ROLE VALUES (1,'ADMIN');
        saveRole(new Role(1,"ADMIN"));
        //    INSERT INTO ROLE VALUES (2,'USER');
        saveRole(new Role(2,"USER"));
    }
    /** Metoda pobierająca wszystkie role */
    public List<Role> getAllRoles(){
        return roleRepository.findAllRoles();
    }

    /** Metoda pobierająca role użytkownika */
    public List<Role> getUserRole() {
        return roleRepository.findUserRole();
    }

    /** Metoda szukająca role */
    public Role findRole(String role) {
        return roleRepository.findRole(role);
    }
}
