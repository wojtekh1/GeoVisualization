package com.handel.geo.repository;

import com.handel.geo.model.Locator;
import com.handel.geo.model.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repozytorium lokalizatorów
 */
@Repository("locatorRepository")
public interface LocatorRepository extends CrudRepository<Locator, Integer>
//        extends JpaRepository<Locator, Integer>
{
    @Query(value = "select * from LOCATOR order by ID desc", nativeQuery = true)
    List<Locator> getAllLocators();

//    @Query(value = "select * from LOCATOR l inner join Users u where u.USER_ID=:userId order by ID desc", nativeQuery = true)
    @Query(value = "select ID from LOCATOR l where USER=:userId order by ID desc", nativeQuery = true)
    List<String> getAllUserLocatorsId(@Param("userId") Integer userId);

    @Query(value = "select * from LOCATOR l where USER=:userId order by NAME", nativeQuery = true)
    List<Locator> getAllUserLocators(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query("delete from Locator l where l.id=:id")
    void deleteLocatorById(@Param("id") Long id);


    @Query(value = "select * from LOCATOR l where l.id=:id", nativeQuery = true)
    Locator getLocatorById(@Param("id") Long id);

    @Query(value = "select l.user from Locator as l where l.apiKey=:apiKey")
    Users getUserByApiKey(@Param("apiKey") String apiKey);

    @Query(value = "select l.id from Locator as l where l.apiKey=:key")
    Long getLocatorIdByApiKey(@Param("key") String key);
}