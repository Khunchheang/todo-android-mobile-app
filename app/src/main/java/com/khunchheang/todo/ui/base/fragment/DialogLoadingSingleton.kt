package com.khunchheang.todo.ui.base.fragment

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.khunchheang.todo.R

class DialogLoadingSingleton {

    private var dialog: Dialog? = null

    fun show(context: Context) {
        if (dialog != null && dialog!!.isShowing) {
            return
        }
        dialog = Dialog(context)
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTransparent)
        dialog!!.setContentView(R.layout.loading_progressbar)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    fun dismiss() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    companion object {
        private var mInstance: DialogLoadingSingleton? = null

        val instance: DialogLoadingSingleton
            @Synchronized get() {
                if (mInstance == null) {
                    mInstance = DialogLoadingSingleton()
                }
                return mInstance!!
            }
    }

}
