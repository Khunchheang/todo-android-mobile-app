package com.khunchheang.todo.ui.mvp.interactorimpl

import android.content.Intent
import com.khunchheang.todo.ui.mvp.ShareApp

class ShareAppInteractorImpl:ShareApp.ShareAppInteractor {
    override fun onShare(): Intent {
        val shareBody = "https://play.google.com/store/apps/details?id=com.instagram.android&hl=en"
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(
            android.content.Intent.EXTRA_SUBJECT, "Todo (Open it in Google Play Store to Download the Application)"
        )
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        return sharingIntent
    }
}