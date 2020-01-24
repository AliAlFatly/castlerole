package com.example.Castlerole.repository;

import com.example.Castlerole.model.User;
import com.example.Castlerole.model.response.GridResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);

	Optional<String> findPictureReferenceByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

	Optional<User> findByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.food = :food, u.wood = :wood, u.stone = :stone, u.iron = :iron WHERE u.username = :username")
	Integer updateResources(@Param("username") String username, @Param("food") int food, @Param("wood") int wood, @Param("stone") int stone, @Param("iron") int iron);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.wood = :amount WHERE u.username = :username")
	Integer updateWood(@Param("amount") int amount, @Param("username") String username);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.iron = :amount WHERE u.username = :username")
	Integer updateIron(@Param("amount") int amount, @Param("username") String username);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.stone = :amount WHERE u.username = :username")
	Integer updateStone(@Param("amount") int amount, @Param("username") String username);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.food = :amount WHERE u.username = :username")
	Integer updateFood(@Param("amount") int amount, @Param("username") String username);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.troops = :amount WHERE u.username = :username")
	Integer updateTroops(@Param("amount") int amount, @Param("username") String username);

	@Transactional
	@Modifying
	@Query("select u from User u where u.coordinateX >= :minX and u.coordinateX <= :maxX and u.coordinateY >= :minY and u.coordinateY <= :maxY")
	Optional<ArrayList<User>> getUsersInGrid(@Param("minX") int minX, @Param("maxX") int maxX, @Param("minY") int minY, @Param("maxY") int maxY);
}