package com.khunchheang.todo.ui.base.basemvp

import com.khunchheang.todo.ui.base.basemvp.response.BaseResponseListener

interface BaseInteractor<T> {

    fun setListener(listener: BaseResponseListener<T>)

    fun onCancelLoading()

}