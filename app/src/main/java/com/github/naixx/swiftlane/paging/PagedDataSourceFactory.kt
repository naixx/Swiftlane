package com.github.naixx.swiftlane.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.Observable

class PagedDataSourceFactory<T>(
        private val func: (Int) -> Observable<out ItemsWithPagination<out T>>
) : DataSource.Factory<Long, T>() {
    val sourceLiveData = MutableLiveData<PagedDataSource<T>>()

    override fun create(): DataSource<Long, T> {
        val source = PagedDataSource<T>(func)
        sourceLiveData.postValue(source)
        return source
    }
}
