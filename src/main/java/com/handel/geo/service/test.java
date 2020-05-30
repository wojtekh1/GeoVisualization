package com.handel.geo.service;

import com.handel.geo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.handel.geo.repository.RoleRepository;
import com.handel.geo.repository.UsersRepository;

import javax.sql.DataSource;

@Component
public class test implements CommandLineRunner {

    @Autowired
    DataSource data;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(bCryptPasswordEncoder.encode("a"));
        System.out.println(bCryptPasswordEncoder.encode("b"));
//        bCryptPasswordEncoder.

//        System.out.println("role"+ usersRepository.findAllUsers());
//        System.out.println("role"+userTypeRepository.findAllRoles());
//        try {
//            connection = DriverManager.getConnection("jdbc:h2:file:~/database/forumdatabase", "sa", "sa");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            Statement statement = connection.createStatement();
//            System.out.println("query");
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM FORUMDATABASE.PUBLIC.FORUMUSERS");
//            while (resultSet.next())
//                System.out.println(resultSet.getInt(1) + " "  + resultSet.getString(2) + " "  + resultSet.getString(3));
//        } catch (SQLException e) {
//            System.out.println("ERROR connection");
//            e.printStackTrace();
//        }
    }
}
