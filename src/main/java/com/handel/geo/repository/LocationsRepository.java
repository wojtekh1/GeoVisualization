package com.handel.geo.repository;

import com.handel.geo.model.Locations;
import com.handel.geo.model.Locator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("locationsRepository")
public interface LocationsRepository extends CrudRepository<Locations, Integer>
{
    @Query(value = "select * from LOCATOIONS order by ID desc", nativeQuery = true)
    List<Locations> getAllLocations();

    @Transactional
    @Modifying
    @Query("delete from Locations l where l.id=:id")
    void deleteLocatoionsById(@Param("id") Integer id);

    @Query(value = "select * from LOCATIONS l where l.id=:id", nativeQuery = true)
    Locator getLocationsById(@Param("id") Integer id);

    @Query(value = "select * from LOCATIONS l where l.USER_ID=:userId order by DATE_TIME", nativeQuery = true)
    Locator getLocationsByUser(@Param("userId") Integer userId);
}