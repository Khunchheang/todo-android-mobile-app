package com.khunchheang.todo.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khunchheang.todo.R
import com.khunchheang.todo.data.model.other.OptionSetModel
import com.khunchheang.todo.ui.base.adapter.BaseAdapter
import com.khunchheang.todo.ui.listener.ItemClickListener
import kotlinx.android.synthetic.main.item_option_set.view.*

class OptionSetAdapter(private val context: Context, items: ArrayList<OptionSetModel>?) :
    BaseAdapter<OptionSetModel, OptionSetAdapter.ViewHolder>(items) {

    var itemClickListener: ItemClickListener? = null

    override fun setViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_option_set, parent, false)
        return ViewHolder(view)
    }

    override fun onBindData(holder: ViewHolder, data: OptionSetModel) {
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView?.setOnClickListener {
                if (adapterPosition == 1) {
                    val optionSet = getItemPosition(adapterPosition)
                    optionSet.isPriority = !optionSet.isPriority
                    notifyItemChanged(adapterPosition)
                }
                itemClickListener?.onItemClicked(it, adapterPosition)
            }
        }

        fun bind(optionSet: OptionSetModel) {
            itemView?.img_icon?.setImageDrawable(ContextCompat.getDrawable(context, optionSet.icon))
            itemView?.tv_title?.text = optionSet.title
            itemView?.tv_sub_title?.text = optionSet.subTitle

            itemView?.img_checked?.visibility = if (optionSet.isPriority) View.VISIBLE else View.GONE
        }
    }
}