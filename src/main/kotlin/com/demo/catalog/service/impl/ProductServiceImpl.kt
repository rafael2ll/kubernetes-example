package com.demo.catalog.service.impl

import com.demo.catalog.core.paging.Filter
import com.demo.catalog.core.paging.Page
import com.demo.catalog.model.dto.CreateProductDto
import com.demo.catalog.model.dto.ProductDto
import com.demo.catalog.model.dto.UpdateProductDto
import com.demo.catalog.repository.ProductRepository
import com.demo.catalog.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(private val productRepository: ProductRepository): ProductService {
    private val log = LoggerFactory.getLogger(ProductServiceImpl::class.java)


    override fun create(product: CreateProductDto): ProductDto {
        val entity = product.toProduct()
        return ProductDto.of(productRepository.save(entity))
    }

    override fun findById(id: Long): ProductDto? {
       val product = productRepository.findById(id)
        return product.takeIf { it.isPresent }?.let { ProductDto.of(it.get()) }
    }

    override fun update(id: Long, product: UpdateProductDto): ProductDto {
       val entity = productRepository.findById(id).orElseThrow{ IllegalArgumentException("Product not found")}
        product.name?.apply { entity.name = this }
        product.price?.apply { entity.price = this }
        product.description?.apply { entity.description = this }
        return ProductDto.of(productRepository.save(entity))
    }

    override fun findAll(filter: Filter): Page<ProductDto> {
        return productRepository.findAll(PageRequest.of(filter.page, filter.size)).let {
            Page(it.content.map(ProductDto::of), it.number, it.size, it.totalPages > it.number )
        }
    }

    override fun delete(id: Long) {
        productRepository.deleteById(id)
    }
}