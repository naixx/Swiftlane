@file:Suppress("UNUSED_PARAMETER")

package com.github.naixx.swiftlane.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.naixx.ItemCallback

open class BindablePagedRecyclerAdapter<T, B : ViewDataBinding> @JvmOverloads constructor(
    @param:LayoutRes private val layoutResId: Int,
    diffCallback: DiffUtil.ItemCallback<T>,
    internal val callback: ItemCallback<T>? = null
) : PagedListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {

    protected val variableId: Int
        get() = com.github.naixx.swiftlane.BR.item

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        protected val binding: B = DataBindingUtil.bind(itemView)!!

        fun bind(item: T?, position: Int) {
            onItemView(item, binding)
            binding.setVariable(variableId, item)
            if (callback != null) {
                itemView.setOnClickListener { v -> callback.onItem(item) }
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false))
    }

    @Suppress("RemoveRedundantQualifierName", "UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindablePagedRecyclerAdapter<T, B>.ViewHolder).bind(getItem(position), position)
    }

    protected fun onItemView(item: T?, binding: B?) {}
}
