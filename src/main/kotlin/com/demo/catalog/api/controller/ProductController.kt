package com.demo.catalog.api.controller;


import com.demo.catalog.api.representation.CreateProductRequest
import com.demo.catalog.api.representation.ProductResponse
import com.demo.catalog.api.representation.UpdateProductRequest
import com.demo.catalog.core.paging.DEFAULT_INITIAL_PAGE
import com.demo.catalog.core.paging.DEFAULT_PAGE_SIZE
import com.demo.catalog.core.paging.Filter
import com.demo.catalog.core.paging.Page
import com.demo.catalog.service.ProductService
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products")
public class ProductController(private val productService: ProductService){
    @PostMapping
    fun create(@RequestBody product: CreateProductRequest): ProductResponse {
        return ProductResponse.of(productService.create(product.toDto()))
    }

    @GetMapping("/{id}")
    fun  findById(@PathVariable("id") id : Long): ProductResponse? {
        return productService.findById(id)?.let { ProductResponse.of(it) };
    }

    @GetMapping
    fun  getProducts(@RequestParam(name= "page", required = false) page: Int = DEFAULT_INITIAL_PAGE, @RequestParam(name="size", required = false) size: Int = DEFAULT_PAGE_SIZE): Page<ProductResponse> {
        return productService.findAll(Filter(page, size)).to (ProductResponse::of)
    }

    @PutMapping("/{id}")
     fun update(@PathVariable("id")  id: Long, @RequestBody product: UpdateProductRequest): ProductResponse {
        return ProductResponse.of(productService.update(id, product.toDto()));
    }

    @DeleteMapping
    fun delete(@RequestParam  id: Long) {
        productService.delete(id);
    }
}
