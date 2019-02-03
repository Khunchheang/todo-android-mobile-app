package com.khunchheang.todo.ui.base.basemvp.baseinteractorimpl

import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.response.BaseResponseListener

abstract class BaseInteractorImpl<T> : BaseInteractor<T> {

    var responseListener: BaseResponseListener<T>? = null

    override fun setListener(listener: BaseResponseListener<T>) {
        this.responseListener = listener
    }
}