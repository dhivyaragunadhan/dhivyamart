package com.dhivya.dhivyamart;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartService(
            CartItemRepository cartItemRepository,
            ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    // Add product to cart
    public void addToCart(Long productId, String buyerEmail, int quantity) {

        CartItem existing = cartItemRepository
                .findByBuyerEmailAndProductId(buyerEmail, productId)
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            cartItemRepository.save(existing);
        } else {
            CartItem item = new CartItem(
                    productId,
                    buyerEmail,
                    quantity
            );

            cartItemRepository.save(item);
        }
    }

    // View cart
    public List<CartItem> getCartItems(String buyerEmail) {
        return cartItemRepository.findByBuyerEmail(buyerEmail);
    }

    // Update quantity
    public void updateQuantity(Long id, int quantity) {

        CartItem item = cartItemRepository
                .findById(id)
                .orElse(null);

        if (item != null) {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
    }

    // Remove item
    public void removeItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    // Calculate total
    public double getTotal(String buyerEmail) {

        List<CartItem> items = getCartItems(buyerEmail);

        double total = 0;

        for (CartItem item : items) {

            Product product =
                    productService.getProduct(item.getProductId());

            if (product != null) {
                total += product.getPrice() * item.getQuantity();
            }
        }

        return total;
    }
}