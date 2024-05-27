package com.demo.catalog.service

import com.demo.catalog.core.paging.Filter
import com.demo.catalog.core.paging.Page
import com.demo.catalog.entity.Product
import com.demo.catalog.model.dto.CreateProductDto
import com.demo.catalog.model.dto.ProductDto
import com.demo.catalog.model.dto.UpdateProductDto

interface ProductService {
    fun create(product: CreateProductDto): ProductDto
    fun findById(id: Long): ProductDto?
    fun update(id: Long, product: UpdateProductDto): ProductDto
    fun findAll(filter: Filter): Page<ProductDto>
    fun delete(id: Long)
}