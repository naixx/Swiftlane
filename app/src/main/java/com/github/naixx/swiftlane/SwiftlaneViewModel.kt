package com.github.naixx.swiftlane

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.github.naixx.swiftlane.network.Hits
import com.github.naixx.swiftlane.network.PixbayApi
import com.github.naixx.swiftlane.paging.Listing
import javax.inject.Inject

class SwiftlaneViewModel @Inject constructor(val api: PixbayApi) : ViewModel() {
    val list: LiveData<PagedList<Hits>>
    val loading: LiveData<Boolean>

    init {
        val listing = Listing.create { api.query("", it) }
        list = listing.pagedList
        loading = listing.loading
    }
}
