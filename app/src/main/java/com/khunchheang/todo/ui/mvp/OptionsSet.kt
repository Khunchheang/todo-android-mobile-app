package com.khunchheang.todo.ui.mvp

import com.khunchheang.todo.data.model.other.OptionSetModel
import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel

interface OptionsSet {

    interface OptionSetInteractor : BaseInteractor<List<OptionSetModel>> {
        fun onGetOptionsSet(completion: (responseModel: ResponseModel) -> Unit)
    }

    interface OptionSetPresenter : BasePresenter<OptionSetView> {
        fun getOptionsSet()
    }

    interface OptionSetView : BaseMvpView {
        fun onOptionsSetResponse(lstOptionsSet: List<OptionSetModel>)
    }

}