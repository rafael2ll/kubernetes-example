package com.demo.catalog.api.representation

import com.demo.catalog.model.dto.CreateProductDto
import com.demo.catalog.model.dto.ProductDto

data class CreateProductRequest(val name: String, val description: String, val price: Double){
    fun toDto(): CreateProductDto {
        return CreateProductDto(name, description, price)
    }
}