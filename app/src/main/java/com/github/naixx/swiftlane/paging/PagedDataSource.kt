package com.github.naixx.swiftlane.paging

import androidx.paging.PageKeyedDataSource
import io.reactivex.Observable

class PagedDataSource<T>(private val func: (Int) -> Observable<out ItemsWithPagination<out T>>) : PageKeyedDataSource<Long, T>() {

    override fun loadInitial(params: LoadInitialParams<Long>,
                             callback: LoadInitialCallback<Long, T>) {
        val result: ItemsWithPagination<out T>? = func(1).blockingFirst(null)

        if (result != null) {
            val nextPage = 2L tryIf { result.totalPages >= 1 }

            if (!params.placeholdersEnabled)
                callback.onResult(result.hits, null, nextPage)
            else
                callback.onResult(result.hits, 1, result.totalPages * params.requestedLoadSize, null, nextPage)
        } else
            callback.onResult(emptyList(), null, null)
    }

    override fun loadBefore(params: LoadParams<Long>,
                            callback: LoadCallback<Long, T>) {
    }

    override fun loadAfter(params: LoadParams<Long>,
                           callback: LoadCallback<Long, T>) {
        val page = params.key.toInt()
        val result: ItemsWithPagination<out T>? = func(page).blockingFirst(null)
        if (result != null) {
            val nextPage = (page + 1L) tryIf { result.totalHits <= 500 }

            callback.onResult(result.hits, nextPage)
        } else
            callback.onResult(emptyList(), null)
    }
}

inline infix fun <T> T?.tryIf(predicate: (T) -> Boolean): T? {
    return if (this != null)
        if (predicate(this)) this else null
    else null
}
