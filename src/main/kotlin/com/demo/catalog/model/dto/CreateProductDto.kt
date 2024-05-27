package com.demo.catalog.model.dto

import com.demo.catalog.entity.Product

class CreateProductDto(private val name: String, private val description: String, private val price: Double){
    fun toProduct(): Product {
        return Product(name = name, description = description, price = price)
    }
}