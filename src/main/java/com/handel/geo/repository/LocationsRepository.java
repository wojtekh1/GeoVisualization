package com.handel.geo.repository;

import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository("locationsRepository")
public interface LocationsRepository extends CrudRepository<Location, Integer>
{
    @Query(value = "select * from LOCATION order by ID desc", nativeQuery = true)
    List<Location> getAllLocations();

    @Transactional
    @Modifying
    @Query("delete from Location l where l.id=:id")
    void deleteLocationById(@Param("id") Integer id);

    @Query(value = "select * from LOCATION l where l.id=:id", nativeQuery = true)
    Location getLocationById(@Param("id") Integer id);

    @Query(value = "select * from LOCATION l where l.LOCATOR=:locatorId order by DATE_TIME", nativeQuery = true)
    ArrayList<Location> getLocationsByUser(@Param("locatorId") List<String> locatorId);

//    @Query(value = "select * from LOCATION l where l.USER_ID=:userId order by DATE_TIME", nativeQuery = true)
//    ArrayList<Location> getLocationsByUserAndDates(@Param("userId") Integer userId);
}