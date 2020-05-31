package com.handel.geo.repository;

import com.handel.geo.model.Locator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("locatorRepository")
public interface LocatorRepository extends CrudRepository<Locator, Integer>
//        extends JpaRepository<Locator, Integer>
{
    @Query(value = "select * from LOCATOR order by ID desc", nativeQuery = true)
    List<Locator> getAllLocators();

    @Transactional
    @Modifying
    @Query("delete from Locator l where l.id=:id")
    void deleteLocatorById(@Param("id") Integer id);


    @Query(value = "select * from LOCATOR l where l.ID=:id", nativeQuery = true)
    Locator getLocatorById(@Param("id") Integer id);
    
}