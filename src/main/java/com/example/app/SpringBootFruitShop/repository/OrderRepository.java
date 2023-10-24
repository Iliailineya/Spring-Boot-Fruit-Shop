package com.example.app.SpringBootFruitShop.repository;

import com.example.app.SpringBootFruitShop.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
