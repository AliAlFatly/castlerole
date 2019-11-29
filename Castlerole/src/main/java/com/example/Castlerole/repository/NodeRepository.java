package com.example.Castlerole.repository;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<Node, Integer> {

    Node findByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);
}
