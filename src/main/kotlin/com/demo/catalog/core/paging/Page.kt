package com.demo.catalog.core.paging

class Page<T> (val items: List<T>, val page: Int, val pageSize: Int, val hasNextPage: Boolean) {}