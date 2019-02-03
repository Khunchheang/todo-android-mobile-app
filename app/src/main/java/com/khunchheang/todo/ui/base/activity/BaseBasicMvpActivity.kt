package com.khunchheang.todo.ui.base.activity

import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.fragment.DialogLoadingSingleton
import javax.inject.Inject

abstract class BaseBasicMvpActivity : BaseBasicActivity(), BaseMvpView {

    @Inject
    lateinit var dialogLoading: DialogLoadingSingleton

    override fun showError(msg: String) {
        dialogLoading.dismiss()
        showToast(msg)
    }

    override fun showException(msg: String) {
        dialogLoading.dismiss()
        showToast(msg)
    }

    override fun showLoading() {
        dialogLoading.dismiss()
        dialogLoading.show(this)
    }

    override fun hideLoading() {
        dialogLoading.dismiss()
    }
}