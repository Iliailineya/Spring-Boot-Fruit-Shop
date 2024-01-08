package com.example.app.SpringBootFruitShop.repository;

import com.example.app.SpringBootFruitShop.entity.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitRepository extends CrudRepository<Match, Long> {
    List<Match> findAll();
}
