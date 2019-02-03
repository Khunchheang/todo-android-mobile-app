package com.khunchheang.todo.ui.mvp

import android.content.Intent

interface ShareApp {

    interface ShareAppInteractor {
        fun onShare(): Intent
    }

    interface ShareAppPresenter {
        fun attach(view: ShareAppView)

        fun detach()

        fun doShare()
    }

    interface ShareAppView {
        fun startShare(intent: Intent)
    }

}