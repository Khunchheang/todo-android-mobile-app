package com.khunchheang.todo.ui.mvp.presenterimpl

import com.khunchheang.todo.data.model.other.OptionSetModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.basepresenterimpl.BasePresenterImpl
import com.khunchheang.todo.ui.mvp.OptionsSet

class OptionSetPresenterImpl (private val optionSetInter: OptionsSet.OptionSetInteractor)
    : BasePresenterImpl<List<OptionSetModel>, OptionsSet.OptionSetView>(), OptionsSet.OptionSetPresenter{

    override fun getOptionsSet() {
        view?.showLoading()

        optionSetInter.onGetOptionsSet{
            onResponseData(it)
        }
    }

    override fun onSuccess(data: List<OptionSetModel>) {
        view?.onOptionsSetResponse(data)
    }

    override fun getInter(): BaseInteractor<List<OptionSetModel>> {
        return optionSetInter
    }
}