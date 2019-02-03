package com.khunchheang.todo.ui.mvp.interactorimpl

import android.content.Context
import com.khunchheang.todo.R
import com.khunchheang.todo.data.model.other.ListOptionSetModel
import com.khunchheang.todo.data.model.other.OptionSetModel
import com.khunchheang.todo.ui.base.basemvp.baseinteractorimpl.BaseInteractorImpl
import com.khunchheang.todo.ui.mvp.OptionsSet
import com.khunchheang.todo.util.Common
import java.util.*

class OptionSetInteractorImpl(private val context: Context, private val currentDate: Date) :
    BaseInteractorImpl<ListOptionSetModel>(),
    OptionsSet.OptionSetInteractor {

    override fun onGetOptionsSet() {
        responseListener?.onSuccess(getListOptionsSet())
    }

    override fun onCancelLoading() {

    }

    private fun getListOptionsSet(): ListOptionSetModel {
        val lstOptionSetModel = ArrayList<OptionSetModel>()

        val dueDate = OptionSetModel(
            R.drawable.ic_today,
            context.getString(R.string.due_date),
            Common.getDisplayMonthAndDay(currentDate),
            false
        )

        val priority = OptionSetModel(
            R.drawable.ic_priority_black_24dp,
            context.getString(R.string.set_priority),
            context.getString(R.string.priority),
            false
        )

        lstOptionSetModel.add(dueDate)
        lstOptionSetModel.add(priority)

        return ListOptionSetModel(lstOptionSetModel)
    }
}