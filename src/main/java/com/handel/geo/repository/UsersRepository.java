package com.handel.geo.repository;

import com.handel.geo.model.Location;
import com.handel.geo.model.Users;
import com.handel.geo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repozytorium użytkowników
 */
@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);

    @Query(value = "select * from Users", nativeQuery = true)
    List<Users> findAllUsers();

    List<Users> findUserByRoles(Role role);

    Users findUsersByUserId(Integer id);
}