package com.dhivya.dhivyamart;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderService(
            OrderRepository orderRepository,
            CartService cartService) {

        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    // Checkout
    public Order checkout(String buyerEmail) {

        double total = cartService.getTotal(buyerEmail);

        Order order = new Order(
                buyerEmail,
                total,
                "PAID",
                "PLACED"
        );

        Order savedOrder = orderRepository.save(order);

        return savedOrder;
    }

    // Order History
    public List<Order> getOrders(String buyerEmail) {

        return orderRepository.findByBuyerEmail(buyerEmail);
    }
}