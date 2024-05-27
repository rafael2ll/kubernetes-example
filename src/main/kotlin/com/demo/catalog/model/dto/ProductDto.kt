package com.demo.catalog.model.dto

import com.demo.catalog.entity.Product

class ProductDto(val id: Long, var name: String, val description: String, val price: Double){
    companion object{
        fun of(product: Product): ProductDto{
            return ProductDto(product.id!!, product.name, product.description, product.price)
        }
    }
}