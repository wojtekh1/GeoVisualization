package com.handel.geo.repository;

import com.handel.geo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozytorium ról użytkowników
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String type);

    @Query(value = "select * from Role", nativeQuery = true)
    List<Role> findAllRoles();

    @Query(value = "select * from Role where TYPE='USER'", nativeQuery = true)
    List<Role> findUserRole();

    @Query(value = "select * from Role where TYPE=:role", nativeQuery = true)
    Role findRole(@Param("role") String role);
}
