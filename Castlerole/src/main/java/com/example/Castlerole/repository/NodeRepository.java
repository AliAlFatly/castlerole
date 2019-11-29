package com.example.Castlerole.repository;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NodeRepository extends CrudRepository<Node, Integer> {

    Optional<String> findPictureReferenceByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);
}
