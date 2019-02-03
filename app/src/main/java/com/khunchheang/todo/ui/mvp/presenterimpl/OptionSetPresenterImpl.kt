package com.khunchheang.todo.ui.mvp.presenterimpl

import com.khunchheang.todo.data.model.other.ListOptionSetModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.basepresenterimpl.BasePresenterImpl
import com.khunchheang.todo.ui.mvp.OptionsSet

class OptionSetPresenterImpl (private val optionSetInter: OptionsSet.OptionSetInteractor)
    : BasePresenterImpl<ListOptionSetModel, OptionsSet.OptionSetView>(), OptionsSet.OptionSetPresenter{

    override fun getOptionsSet() {
        view?.showLoading()
        optionSetInter.onGetOptionsSet()
    }

    override fun onSuccess(data: ListOptionSetModel) {
        view?.hideLoading()
        view?.onOptionsSetResponse(data.lstOptionsSet)
    }

    override fun getInter(): BaseInteractor<ListOptionSetModel> {
        return optionSetInter
    }
}