package com.example.gccoffee.Service;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.ProductJdbcRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultProductService implements ProductService {

    private final ProductJdbcRepository productJdbcRepository;

    public DefaultProductService(ProductJdbcRepository productJdbcRepository) {
        this.productJdbcRepository = productJdbcRepository;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productJdbcRepository.findByCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productJdbcRepository.findAll();
    }

    @Override
    public Product createProduct(String productName, Category category, long price) {
        var product = new Product(UUID.randomUUID(), productName, category, price);
        return productJdbcRepository.insert(product);
    }

    @Override
    public Product createProduct(String productName, Category category, long price, String description) {
        var product = new Product(UUID.randomUUID(), productName, category, price, description, LocalDateTime.now(), LocalDateTime.now());
        return productJdbcRepository.insert(product);
    }
}
