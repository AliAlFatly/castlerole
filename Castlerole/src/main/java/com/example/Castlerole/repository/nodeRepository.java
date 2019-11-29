package com.example.Castlerole.repository;

import com.example.Castlerole.model.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface nodeRepository extends CrudRepository<Node, Integer> {

}
