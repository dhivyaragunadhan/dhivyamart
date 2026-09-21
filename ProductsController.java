package com.dhivya.dhivyamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products/add")
    @ResponseBody
    public Product addProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int quantity,
            HttpSession session) {

        String sellerEmail =
                (String) session.getAttribute("userEmail");

        Product product = new Product(
                name,
                description,
                price,
                quantity,
                sellerEmail
        );

        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> products(HttpSession session) {

        String sellerEmail =
                (String) session.getAttribute("userEmail");

        return productService.getSellerProducts(sellerEmail);
    }

    @GetMapping("/products/all")
    @ResponseBody
    public List<Product> allProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/products/{id}")
    @ResponseBody
    public Product updateProduct(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int quantity) {

        Product product = productService.getProduct(id);

        if (product == null) {
            return null;
        }

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        return productService.saveProduct(product);
    }

    @DeleteMapping("/products/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }

    @GetMapping("/products-page")
    public ResponseEntity<Resource> productsPage() {

        Resource resource =
                new ClassPathResource("static/products.html");

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/seller-products")
    public ResponseEntity<Resource> sellerProducts() {

        Resource resource =
                new ClassPathResource("static/seller-products.html");

        return ResponseEntity.ok(resource);
    }
}