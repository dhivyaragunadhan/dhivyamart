package com.dhivya.dhivyamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Checkout
    @PostMapping("/checkout")
    public Order checkout(HttpSession session) {

        String buyerEmail =
                (String) session.getAttribute("userEmail");

        if (buyerEmail == null) {
            return null;
        }

        return orderService.checkout(buyerEmail);
    }

    // Order History
    @GetMapping("/orders")
    public List<Order> getOrders(HttpSession session) {

        String buyerEmail =
                (String) session.getAttribute("userEmail");

        if (buyerEmail == null) {
            return List.of();
        }

        return orderService.getOrders(buyerEmail);
    }
}