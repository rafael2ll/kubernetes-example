package com.demo.catalog.api.controller;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {
    private final ProductService ProductService;
    public ProductController(ProductService ProductService) {
        this.ProductService = ProductService;
    }
    @PostMapping
    public ProductResponse create(@RequestBody CreateProductRequest Product) {
        return ProductResponse.fromDto(ProductService.create(Product.toDto()));
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable("id") Integer id) {
        return ProductResponse.fromDto(ProductService.findById(id));
    }

    @GetMapping
    public List<ProductResponse> getProducts(@RequestParam int page) {
        return ProductService.findAllPaged(page).stream().map(ProductResponse::fromDto).toList();
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable("id") Integer id, @RequestBody UpdateProductRequest Product) {
        return ProductResponse.fromDto(ProductService.update(id, Product.toDto()));
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        ProductService.delete(id);
    }
}
