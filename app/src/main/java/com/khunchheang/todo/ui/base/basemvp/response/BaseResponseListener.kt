package com.khunchheang.todo.ui.base.basemvp.response

interface BaseResponseListener<T> {

    fun onError(msg: String)

    fun onException(msg: String)

    fun onSuccess(data: T)

}