package com.example.demo.service;

import com.example.demo.producer.OrderEventProducer;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderEventProducer orderEventProducer;
    
    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.repository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }

    public Order createOrder(String product, Double amount) {

        Order order = new Order();
        order.setProduct(product);
        order.setAmount(amount);
        order.setStatus("NEW");
        order.setCreatedAt(LocalDateTime.now());

        Order saved = repository.save(order);

        String event = """
                {
                  "orderId": %d,
                  "product": "%s",
                  "amount": %s
                }
                """.formatted(saved.getId(), product, amount);

        orderEventProducer.sendOrderCreated(event);

        return saved;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}