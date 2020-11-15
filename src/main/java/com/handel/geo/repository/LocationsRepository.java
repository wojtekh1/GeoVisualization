package com.handel.geo.repository;

import com.handel.geo.model.Location;
import com.handel.geo.model.Locator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    @Transactional
    @Modifying
    @Query(value = "delete from Location l where l.locator=:locator", nativeQuery = true)
    void deleteAllLocatorLocation(@Param("locator") Locator locator);

    @Query(value = "select * from LOCATION l where l.id=:id", nativeQuery = true)
    Location getLocationById(@Param("id") Integer id);

    @Query(value = "select l.*  from LOCATION l where l.LOCATOR in(:locatorId) order by DATE_TIME", nativeQuery = true)
    ArrayList<Location> getLocationsByUser(@Param("locatorId") List<String> locatorId);

    @Query(value = "select l.* from LOCATION l join (select distinct max(ll.DATE_TIME) as DATA," +
            "ll.LOCATOR as LOCALIZATOR from LOCATION ll group by ll.LOCATOR) on (l.DATE_TIME=DATA " +
            "and l.LOCATOR=LOCALIZATOR) where l.LOCATOR in(:locatorId)", nativeQuery = true)
    ArrayList<Location> getLastLocatorLocations(@Param("locatorId") List<String> locatorId);

    @Query(value = "select l.* from LOCATION l where l.LOCATOR in(:locatorId)", nativeQuery = true)
    ArrayList<Location> getLocatorLocations(@Param("locatorId") Long locatorId);

    @Query(value = "select l.* from LOCATION l where l.LOCATOR in(:locatorId) and l.DATE_TIME between :fromDate and :toDate", nativeQuery = true)
    ArrayList<Location> getLocatorLocationsByDate(@Param("locatorId") Long locatorId, @Param("fromDate") LocalDateTime from,@Param("toDate")  LocalDateTime to);



//    @Query(value = "select * from LOCATION l where l.USER_ID=:userId order by DATE_TIME", nativeQuery = true)
//    ArrayList<Location> getLocationsByUserAndDates(@Param("userId") Integer userId);
}