package com.handel.geo.service;

import com.handel.geo.model.Users;
import com.handel.geo.repository.LocatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.handel.geo.repository.RoleRepository;
import com.handel.geo.repository.UsersRepository;
import org.thymeleaf.expression.Strings;

import javax.sql.DataSource;
import java.util.UUID;

@Component
public class test implements CommandLineRunner {

    @Autowired
    DataSource data;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LocatorRepository locatorRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /** Metoda testowa uruchamiana przy starcie apliakacji */
    @Override
    public void run(String... args) throws Exception {
//        System.out.println(bCryptPasswordEncoder.encode("a"));
//        System.out.println(bCryptPasswordEncoder.encode("b"));
//        Users usr = new Users();
//        System.out.println(usr.getRoles());
//        System.out.println(new FileSystemResource("").getFile().getAbsolutePath());
    }
}
