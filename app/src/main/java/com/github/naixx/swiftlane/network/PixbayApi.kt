package com.github.naixx.swiftlane.network

import com.github.naixx.swiftlane.paging.ItemsWithPagination
import com.squareup.moshi.JsonClass
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

@JsonClass(generateAdapter = true)
data class Hits(
        val largeImageURL: String,
        val webformatHeight: Int,
        val webformatWidth: Int,
        val likes: Int,
        val imageWidth: Int,
        val id: Int,
        val user_id: Int,
        val views: Int,
        val comments: Int,
        val pageURL: String,
        val imageHeight: Int,
        val webformatURL: String,
        val type: String,
        val previewHeight: Int,
        val tags: String,
        val downloads: Int,
        val user: String,
        val favorites: Int,
        val imageSize: Int,
        val previewWidth: Int,
        val userImageURL: String,
        val previewURL: String
)

interface PixbayApi {

    @GET("?")
    fun query(@Query("q") queryString: String, @Query("page") page: Int): Observable<ItemsWithPagination<Hits>>
}
