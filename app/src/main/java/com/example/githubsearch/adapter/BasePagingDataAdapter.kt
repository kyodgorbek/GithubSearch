package com.example.githubsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.githubsearch.data.model.PagingItem

abstract class BasePagingDataAdapter<T : PagingItem, VB : ViewBinding> :
    PagingDataAdapter<T, BasePagingDataAdapter<T, VB>.BaseViewHolder>(object :
        DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
    }) {

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected abstract fun bind(data: T, binding: VB)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position) ?: throw IllegalArgumentException("Item cannot be null"))
    }

    inner class BaseViewHolder(private val itemBinding: VB) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: T) {
            bind(data, itemBinding)
        }

    }

}