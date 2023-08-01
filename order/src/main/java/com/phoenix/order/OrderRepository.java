package com.phoenix.order;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

  Optional<Order> findById(String id);


}
