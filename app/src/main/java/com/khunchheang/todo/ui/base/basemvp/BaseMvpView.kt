package com.khunchheang.todo.ui.base.basemvp

interface BaseMvpView {

    fun showError(msg: String)

    fun showException(msg: String)

    fun showLoading()

    fun hideLoading()

}