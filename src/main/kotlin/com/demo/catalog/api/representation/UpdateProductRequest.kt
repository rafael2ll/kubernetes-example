package com.demo.catalog.api.representation

import com.demo.catalog.model.dto.UpdateProductDto

data class UpdateProductRequest(val name: String,val description: String, val price: Double){
        fun toDto() = UpdateProductDto(name, description, price)
}