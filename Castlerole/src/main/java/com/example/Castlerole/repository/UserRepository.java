package com.example.Castlerole.repository;

import com.example.Castlerole.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);

	Optional<String> findPictureReferenceByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

	Optional<User> findByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);
	
}