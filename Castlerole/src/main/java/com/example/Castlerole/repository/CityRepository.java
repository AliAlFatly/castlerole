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

    City findByOwner(String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.casteLevel = :amount WHERE c.owner = :owner")
    Integer updateCastleLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.woodworksLevel = :amount WHERE c.owner = :owner")
    Integer updateWoodworksLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.mineLevel = :amount WHERE c.owner = :owner")
    Integer updateMineLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.forgeryLevel = :amount WHERE c.owner = :owner")
    Integer updateForgeryLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.barracksLevel = :amount WHERE c.owner = :owner")
    Integer updateBarracksLevel(@Param("amount") int amount, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.ovenLevel = :amount WHERE c.owner = :owner")
    Integer updateOvensLevel(@Param("amount") int amount, @Param("owner") String owner);

}
