package com.khunchheang.todo.ui.base.basemvp.basepresenterimpl

import com.khunchheang.todo.ui.base.basemvp.BaseInteractor
import com.khunchheang.todo.ui.base.basemvp.BaseMvpView
import com.khunchheang.todo.ui.base.basemvp.BasePresenter
import com.khunchheang.todo.ui.base.basemvp.response.BaseResponseListener

abstract class BasePresenterImpl<T, V : BaseMvpView> : BasePresenter<V>, BaseResponseListener<T> {

    var view: V? = null

    override fun attach(view: V) {
        this.view = view
        getInter().setListener(this)
    }

    override fun cancelLoading() {
        view?.hideLoading()
        getInter().onCancelLoading()
    }

    override fun detach() {
        view = null
        getInter().onCancelLoading()
    }

    override fun onError(msg: String) {
        view?.hideLoading()
        view?.showError(msg)
    }

    override fun onException(msg: String) {
        view?.hideLoading()
        view?.showException(msg)
    }

    protected abstract fun getInter(): BaseInteractor<T>
}