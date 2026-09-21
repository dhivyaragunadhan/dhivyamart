package com.dhivya.dhivyamart;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String buyerEmail;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Long productId, String buyerEmail, int quantity) {
        this.productId = productId;
        this.buyerEmail = buyerEmail;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}