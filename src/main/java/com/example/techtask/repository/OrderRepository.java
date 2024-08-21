package com.example.techtask.repository;

import com.example.techtask.model.Order;
import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query("SELECT o FROM Order o " +
            "WHERE o.quantity > 1 " +
            "ORDER BY o.createdAt DESC " +
            "LIMIT 1")
    Order findLastOrderWithMoreOneQuantity();

    @Query("SELECT o FROM Order o " +
            "INNER JOIN User u ON u.id = o.userId " +
            "WHERE u.userStatus = 'ACTIVE' " +
            "ORDER BY o.createdAt DESC ")
    List<Order> findOrdersOfActiveUsersOrderByCreatedAtDesc();
}
