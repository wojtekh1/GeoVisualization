package com.handel.geo.service;


import com.handel.geo.model.Users;
import com.handel.geo.model.Role;
import com.handel.geo.repository.RoleRepository;
import com.handel.geo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}