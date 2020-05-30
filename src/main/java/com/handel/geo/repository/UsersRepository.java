package com.handel.geo.repository;

import com.handel.geo.model.Users;
import com.handel.geo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);

    @Query(value = "select * from ForumUsers", nativeQuery = true)
    List<Users> findAllUsers();

    List<Users> findUserByRoles(Role role);

    Users findUsersByUserId(Integer id);
}