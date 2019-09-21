package com.github.naixx.swiftlane

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.github.naixx.swiftlane.network.Hits
import com.github.naixx.swiftlane.paging.BindablePagedRecyclerAdapter

class HitsAdapter() :
    BindablePagedRecyclerAdapter<Hits, ViewDataBinding>(
        R.layout.hits_listitem,
        object : DiffUtil.ItemCallback<Hits>() {
            override fun areItemsTheSame(oldItem: Hits, newItem: Hits): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Hits, newItem: Hits): Boolean =
                oldItem == newItem
        })
