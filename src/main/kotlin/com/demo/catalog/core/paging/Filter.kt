package com.demo.catalog.core.paging

const val DEFAULT_PAGE_SIZE = 20
const val DEFAULT_INITIAL_PAGE = 0

class Filter(val page: Int = DEFAULT_INITIAL_PAGE, val size: Int =  DEFAULT_PAGE_SIZE)