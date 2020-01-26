package com.example.castlerole.repository;

import com.example.castlerole.model.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CityRepository extends CrudRepository<City, Integer>{

    City findById(long id);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.castleLevel = :amount WHERE c.user.id = :id")
    Integer updateCastleLevel(@Param("amount") int amount, @Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.woodworksLevel = :amount WHERE c.user.id = :id")
    Integer updateWoodworksLevel(@Param("amount") int amount, @Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.mineLevel = :amount WHERE c.user.id = :id")
    Integer updateMineLevel(@Param("amount") int amount, @Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.forgeryLevel = :amount WHERE c.user.id = :id")
    Integer updateForgeryLevel(@Param("amount") int amount, @Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.barracksLevel = :amount WHERE c.user.id = :id")
    Integer updateBarracksLevel(@Param("amount") int amount, @Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE City c SET c.ovenLevel = :amount WHERE c.user.id = :id")
    Integer updateOvensLevel(@Param("amount") int amount, @Param("id") long id);

}