package com.example.Castlerole.repository;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.response.GridResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface NodeRepository extends CrudRepository<Node, Integer> {

    Optional<String> findPictureReferenceByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

    Optional<Node> findByCoordinateXAndCoordinateY(int coordinateX, int coordinateY);

    @Transactional
    @Modifying
    @Query("select n from Node n where n.coordinateX >= :minX and n.coordinateX <= :maxX and n.coordinateY >= :minY and n.coordinateY <= :maxY")
    Optional<ArrayList<Node>> getNodesInGrid(@Param("minX") int minX, @Param("maxX") int maxX, @Param("minY") int minY, @Param("maxY") int maxY);
}
