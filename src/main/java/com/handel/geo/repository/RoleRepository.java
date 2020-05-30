package com.handel.geo.repository;

import com.handel.geo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String type);

    @Query(value = "select * from Role", nativeQuery = true)
    List<Role> findAllRoles();

    @Query(value = "select * from Role where TYPE='USER'", nativeQuery = true)
    List<Role> findUserRole();
}
