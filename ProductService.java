package com.dhivya.dhivyamart;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

private final ProductRepository productRepository;

public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
}

// Add Product
public Product saveProduct(Product product) {
    return productRepository.save(product);
}

// Get Seller Products
public List<Product> getSellerProducts(String sellerEmail) {
    return productRepository.findBySellerEmail(sellerEmail);
}

// Get One Product
public Product getProduct(Long id) {
    return productRepository.findById(id).orElse(null);
}

// Delete Product
public void deleteProduct(Long id) {
    productRepository.deleteById(id);
}

// Get All Products - Buyer
public List<Product> getAllProducts() {
    return productRepository.findAll();
}

}
