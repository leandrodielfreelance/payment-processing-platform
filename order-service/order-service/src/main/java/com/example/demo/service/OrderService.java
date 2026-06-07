package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(String product, Double amount) {
        Order order = new Order();
        order.setProduct(product);
        order.setAmount(amount);
        order.setStatus("NEW");
        order.setCreatedAt(LocalDateTime.now());
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}