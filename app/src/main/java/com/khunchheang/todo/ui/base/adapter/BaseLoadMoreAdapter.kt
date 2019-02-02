package com.khunchheang.todo.ui.base.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khunchheang.todo.R
import com.khunchheang.todo.ui.listener.OnLoadFirstListener
import com.khunchheang.todo.ui.listener.OnLoadMoreListener
import com.khunchheang.todo.util.AppConstants
import kotlinx.android.synthetic.main.load_more_progressbar.view.*

abstract class BaseLoadMoreAdapter<T, VH : RecyclerView.ViewHolder>(
    private val dataItems: ArrayList<T?>,
    private val mOnLoadMoreListener: OnLoadMoreListener,
    private val mRecyclerView: RecyclerView,
    private val loadFirstListener: OnLoadFirstListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    var isLoading = true
    var isAll = false

    abstract fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun onBindData(holder: VH, data: T, position: Int)

    fun checkPagination() {
        if (this.itemCount == 0) {
            loadFirstListener.onFirstLoading()
        }
        if (mRecyclerView.layoutManager is LinearLayoutManager) {
            val layoutManager = mRecyclerView.layoutManager as LinearLayoutManager
            mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = layoutManager.itemCount
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (!isAll && !isLoading && totalItemCount <= lastVisibleItem + AppConstants.QUERY_LIMIT) {
                        isLoading = true
                        mOnLoadMoreListener.onLoadMore()
                    }
                }
            })
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataItems[position] != null) VIEW_ITEM else VIEW_PRO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM) {
            setViewHolder(parent)
        } else {
            ProgressViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.load_more_progressbar,
                    parent,
                    false
                )
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProgressViewHolder) {
            holder.itemView.progressbar!!.visibility = if (isAll) View.GONE else View.VISIBLE
            holder.itemView.txt_msg!!.visibility = if (isAll) View.VISIBLE else View.GONE
        } else {
            val viewHolder = holder as VH
            onBindData(viewHolder, dataItems[position]!!, position)
        }
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    open fun addItems(dataItem: List<T>) {
        removeBottomPb()
        isAll = dataItem.size < AppConstants.QUERY_LIMIT
        isLoading = false
        dataItems.addAll(dataItem)
        notifyItemInserted(itemCount)
        if (isAll) enableLoadingBottom()
    }

    fun removeBottomPb() {
        if (itemCount != 0) {
            dataItems.removeAt(dataItems.size - 1)
            notifyItemRemoved(dataItems.size)
        }
    }

    fun enableLoadingBottom() {
        if (itemCount != 0) {
            dataItems.add(null)
            mRecyclerView.post {
                notifyItemInserted(itemCount)
            }
        }
    }

    fun clear() {
        isAll = false
        dataItems.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return dataItems[position]!!
    }

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private val VIEW_ITEM = 1
        private val VIEW_PRO = 0
    }
}
