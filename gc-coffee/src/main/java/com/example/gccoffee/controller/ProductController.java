package com.example.gccoffee.controller;

import com.example.gccoffee.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public String productPage(Model model) {
        var products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping(value = "new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping(value = "/products")
    public String newProduct(CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest.getProductName(),
                createProductRequest.getCategory(),
                createProductRequest.getPrice(),
                createProductRequest.getDescription());
        return "redirect:/products";
    }
}
