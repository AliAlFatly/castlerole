package com.example.Castlerole.repository;

import com.example.Castlerole.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);

	Optional<String> findPictureReferenceByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

	Optional<User> findByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

	@Modifying
	@Query("UPDATE User u SET u.wood = :amount WHERE u.username = :username")
	User updateWood(@Param("amount") int amount, @Param("username") String username);

	@Modifying
	@Query("UPDATE User u SET u.iron = :amount WHERE u.username = :username")
	User updateIron(@Param("amount") int amount, @Param("username") String username);

	@Modifying
	@Query("UPDATE User u SET u.stone = :amount WHERE u.username = :username")
	User updateStone(@Param("amount") int amount, @Param("username") String username);

	@Modifying
	@Query("UPDATE User u SET u.food = :amount WHERE u.username = :username")
	User updateFood(@Param("amount") int amount, @Param("username") String username);
}