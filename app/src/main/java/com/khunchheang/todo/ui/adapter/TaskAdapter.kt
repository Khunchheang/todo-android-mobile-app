package com.khunchheang.todo.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khunchheang.todo.R
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.base.adapter.BaseAdapter
import com.khunchheang.todo.ui.listener.ItemClickListener
import com.khunchheang.todo.util.Common
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(private val context: Context, items: ArrayList<TaskModel>?) :
    BaseAdapter<TaskModel, TaskAdapter.ViewHolder>(items) {

    var itemClickListener: ItemClickListener? = null

    override fun setViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindData(holder: ViewHolder, data: TaskModel) {
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView?.setOnClickListener {
                itemClickListener?.onItemClicked(it, adapterPosition)
            }
        }

        fun bind(taskModel: TaskModel) {
            val dueDate =
                if (taskModel.dueDate != null) Common.getDisplayMonthAndDay(taskModel.dueDate!!) else context.getString(
                    R.string.none
                )
            itemView.tv_date.text = dueDate
            itemView.tv_task.text = taskModel.task
        }
    }
}