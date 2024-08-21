package com.example.techtask.repository;

import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.id = " +
            "(SELECT o.userId FROM Order o " +
            "WHERE o.orderStatus = 'DELIVERED' AND YEAR(o.createdAt) = 2003 " +
            "GROUP BY o.userId ORDER BY SUM(o.price * o.quantity) DESC " +
            "LIMIT 1)")
    User findUserWithMaxDeliveredSummaryInYear2003();

    @Query("SELECT u FROM User u " +
            "INNER JOIN Order o ON u.id = o.userId " +
            "WHERE o.orderStatus = 'PAID' AND YEAR(o.createdAt) = 2010 " +
            "GROUP BY u.id")
    List<User> findUsersWithPaidOrdersInYear2010();
}
