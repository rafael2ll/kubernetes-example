package com.demo.catalog.api.representation

import com.demo.catalog.entity.Product
import com.demo.catalog.model.dto.ProductDto

data class ProductResponse(val id: Long, val name: String, val description: String, val price: Double){
    companion object{
        fun of(product: ProductDto): ProductResponse{
            return ProductResponse(product.id, product.name, product.description, product.price)
        }
    }
}
