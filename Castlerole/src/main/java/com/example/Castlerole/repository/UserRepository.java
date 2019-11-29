package com.example.Castlerole.repository;

import com.example.Castlerole.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);

	User findByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

	
}