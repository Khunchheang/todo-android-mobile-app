package com.khunchheang.todo.ui.mvp.presenterimpl

import com.khunchheang.todo.ui.mvp.ShareApp

class ShareAppPresenterImpl(private val shareAppInter: ShareApp.ShareAppInteractor) : ShareApp.ShareAppPresenter {

    var view: ShareApp.ShareAppView? = null

    override fun attach(view: ShareApp.ShareAppView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun doShare() {
        val intent = shareAppInter.onShare()
        this.view?.startShare(intent)
    }
}