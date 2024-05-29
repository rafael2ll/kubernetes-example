package com.demo.catalog.core.paging

data class Page<T> (val items: List<T>, val page: Int, private val pageSize: Int, val hasNextPage: Boolean) {
           fun <B> to(converter: (a: T)-> B): Page<B>{
               return Page(items.map(converter), page, pageSize, hasNextPage)
        }
}