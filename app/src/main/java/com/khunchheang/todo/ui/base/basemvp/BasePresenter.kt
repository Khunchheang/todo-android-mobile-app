package com.khunchheang.todo.ui.base.basemvp

interface BasePresenter<V : BaseMvpView> {

    fun attach(view: V)

    fun cancelLoading()

    fun detach()

}