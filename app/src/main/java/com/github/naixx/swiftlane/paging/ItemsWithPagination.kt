package com.github.naixx.swiftlane.paging

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ItemsWithPagination<T>(
    val hits: List<T>,
    val totalHits: Int,
    val total: Int
) {
    val totalPages = total / 500
}
