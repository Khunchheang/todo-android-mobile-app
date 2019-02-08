package com.khunchheang.todo.ui.base.basemvp.basepresenterimpl

import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter
import com.khunchheang.todo.ui.base.basemvp.response.ErrorResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.ExceptionResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.ResponseModel
import com.khunchheang.todo.ui.base.basemvp.response.SuccessResponseModel

abstract class BasePresenterImpl<T, V : BaseMvpView> : BasePresenter<V> {

    var view: V? = null

    override fun attach(view: V) {
        this.view = view
    }

    override fun cancelLoading() {
        view?.hideLoading()
        getInter().onCancelLoading()
    }

    override fun detach() {
        view = null
        getInter().onCancelLoading()
    }

    override fun onResponseData(response: ResponseModel) {
        view?.hideLoading()

        when (response) {
            is SuccessResponseModel<*> -> {
                @Suppress("UNCHECKED_CAST")
                onSuccess(response.data as T)
            }
            is ExceptionResponseModel -> {
                view?.showException(response.msg)
            }
            is ErrorResponseModel -> {
                view?.showError(response.msg)
            }
        }
    }

    protected abstract fun onSuccess(data: T)

    protected abstract fun getInter(): BaseInteractor<T>
}