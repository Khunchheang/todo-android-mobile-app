package com.khunchheang.todo.ui.mvp

import com.khunchheang.todo.data.model.other.ListOptionSetModel
import com.khunchheang.todo.data.model.other.OptionSetModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter

interface OptionsSet {

    interface OptionSetInteractor : BaseInteractor<ListOptionSetModel> {
        fun onGetOptionsSet()
    }

    interface OptionSetPresenter : BasePresenter<OptionSetView> {
        fun getOptionsSet()
    }

    interface OptionSetView : BaseMvpView {
        fun onOptionsSetResponse(lstOptionsSet: List<OptionSetModel>)
    }

}