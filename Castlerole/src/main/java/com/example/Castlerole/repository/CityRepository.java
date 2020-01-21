package com.example.Castlerole.repository;

import com.example.Castlerole.model.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CityRepository extends CrudRepository<City, Integer>{

    City findByUserUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.castleLevel = :amount WHERE c.user.username = :owner")
    Integer updateCastleLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.woodworksLevel = :amount WHERE c.user.username = :owner")
    Integer updateWoodworksLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.mineLevel = :amount WHERE c.user.username = :owner")
    Integer updateMineLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.forgeryLevel = :amount WHERE c.user.username = :owner")
    Integer updateForgeryLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.barracksLevel = :amount WHERE c.user.username = :owner")
    Integer updateBarracksLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.ovenLevel = :amount WHERE c.user.username = :owner")
    Integer updateOvensLevel(@Param("amount") int amount, @Param("owner") String owner);

}