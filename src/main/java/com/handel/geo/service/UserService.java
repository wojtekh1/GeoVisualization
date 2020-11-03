package com.handel.geo.service;


import com.handel.geo.model.Users;
import com.handel.geo.model.Role;
import com.handel.geo.repository.RoleRepository;
import com.handel.geo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {

    private UsersRepository usersRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Users findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Integer findUserIdByEmail(String email) {
        Users user = new Users();
        user=usersRepository.findByEmail(email);
        Integer id;
        if (user == null)
        {
            id = 0;
        }
        else {
            id = user.getUserId();
        }
        return id;
    }

    public List<Role> findAllTypes() {
        return roleRepository.findAllRoles();
    }

    public Users saveNewUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(1);

        return usersRepository.save(user);
    }

    public Users updateUser(Users user){
        return usersRepository.save(user);
    }


    public List<Users> findUserByRoles(Role role) {
        return usersRepository.findUserByRoles(role);

    }


    public List<Users> findAll() {
        return usersRepository.findAll();

    }

    public Users findUsersByUserId(Integer id) {
        return usersRepository.findUsersByUserId(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillTestData() {
//        System.out.println("ROLEEEE: "+roleRepository.findRole("ADMIN"));
//        Users user = new Users();
//        user.setPassword("a");
//        user.setEmail("a");
//        user.setRoles(roleRepository.findRole("ADMIN"));
//        saveNewUser(user);
//
//        Users user2 = new Users();
//        user2.setPassword("b");
//        user2.setEmail("b");
//        user2.setRoles(roleRepository.findRoleId("ADMIN"));
//        saveNewUser(user2);
    }
}