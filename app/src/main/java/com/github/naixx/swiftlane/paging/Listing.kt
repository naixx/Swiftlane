package com.github.naixx.swiftlane.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.Observable

data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val loading: LiveData<Boolean>
) {
    companion object {
        fun <T> create(func: (Int) -> Observable<out ItemsWithPagination<out T>>): Listing<T> {
            val loading = MutableLiveData<Boolean>()
            val factory = PagedDataSourceFactory {
                func.invoke(it)
                        .doOnSubscribe { loading.postValue(true) }
                        .doOnDispose { loading.postValue(false) }
            }

            val liveData = LivePagedListBuilder(factory,
                    PagedList.Config.Builder()
                            .setEnablePlaceholders(false)
                            .setPageSize(10)
                            .build())
                    .build()
            return Listing(liveData, Transformations.switchMap(factory.sourceLiveData, { loading }))
        }
    }
}
