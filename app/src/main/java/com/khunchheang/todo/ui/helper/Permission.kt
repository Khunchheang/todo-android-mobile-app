package com.khunchheang.todo.ui.helper

import android.content.Context
import com.khunchheang.todo.ui.listener.PermissionListener

interface Permission {

    fun requestPermission(context: Context, permissionListener: PermissionListener)

    fun permissionResult(grantResults: IntArray, permissionListener: PermissionListener)

    fun isPermissionGranted(context: Context): Boolean

}