package com.khunchheang.todo.ui.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder>constructor(var items: ArrayList<T>?) : RecyclerView.Adapter<VH>() {

    abstract fun setViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract fun onBindData(holder: VH, data: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return setViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindData(holder, items!![position])
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun addItems(data: ArrayList<T>) {
        items = data
        this.notifyDataSetChanged()
    }

    fun addItem(data: T) {
        items?.add(data)
        this.notifyDataSetChanged()
    }

    fun clearItems() {
        items!!.clear()
        this.notifyDataSetChanged()
    }

    fun getItemPosition(position: Int): T {
        return items!![position]
    }
}
