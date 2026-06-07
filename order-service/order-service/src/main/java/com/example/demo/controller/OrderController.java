package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order create(@RequestBody OrderRequest request) {
        return service.createOrder(request.product(), request.amount());
    }

    @GetMapping
    public List<Order> getAll() {
        return service.getAllOrders();
    }
    
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return service.getOrderById(id);
    }
}