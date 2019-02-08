package com.khunchheang.todo.ui.base.basemvp

import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel

interface BasePresenter<V : BaseMvpView> {

    fun attach(view: V)

    fun cancelLoading()

    fun detach()

    fun onResponseData(response: ResponseModel)

}