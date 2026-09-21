package com.dhivya.dhivyamart;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByBuyerEmail(String buyerEmail);

    Optional<CartItem> findByBuyerEmailAndProductId(
            String buyerEmail,
            Long productId
    );
}