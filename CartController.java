package com.dhivya.dhivyamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity,
            HttpSession session) {

        String buyerEmail =
                (String) session.getAttribute("userEmail");

        System.out.println(
                "ADD TO CART BUYER EMAIL = " + buyerEmail
        );

        if (buyerEmail == null) {
            return "Please login first";
        }

        cartService.addToCart(
                productId,
                buyerEmail,
                quantity
        );

        return "Product added to cart successfully!";
    }

    @GetMapping("/cart/items")
    public List<CartItem> getCartItems(
            HttpSession session) {

        String buyerEmail =
                (String) session.getAttribute("userEmail");

        System.out.println(
                "CART BUYER EMAIL = " + buyerEmail
        );

        if (buyerEmail == null) {
            return List.of();
        }

        return cartService.getCartItems(buyerEmail);
    }

    @PutMapping("/cart/{id}")
    public String updateQuantity(
            @PathVariable Long id,
            @RequestParam int quantity) {

        cartService.updateQuantity(id, quantity);

        return "Cart updated successfully!";
    }

    @DeleteMapping("/cart/{id}")
    public String removeItem(
            @PathVariable Long id) {

        cartService.removeItem(id);

        return "Item removed from cart!";
    }

    @GetMapping("/cart/total")
    public double getTotal(
            HttpSession session) {

        String buyerEmail =
                (String) session.getAttribute("userEmail");

        if (buyerEmail == null) {
            return 0;
        }

        return cartService.getTotal(buyerEmail);
    }

    @GetMapping("/cart")
    public ResponseEntity<Resource> cartPage() {

        Resource resource =
                new ClassPathResource(
                        "static/cart.html"
                );

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/checkout-page")
    public ResponseEntity<Resource> checkoutPage() {

        Resource resource =
                new ClassPathResource(
                        "static/checkout.html"
                );

        return ResponseEntity.ok(resource);
    }
}