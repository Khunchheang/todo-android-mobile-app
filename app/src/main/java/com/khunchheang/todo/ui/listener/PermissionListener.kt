package com.khunchheang.todo.ui.listener

interface PermissionListener {

    fun startRequestPermission(permissions: Array<String>, resultPermissionCode: Int)

    fun onPermissionGranted(resultPermissionCode: Int)

    fun onPermissionDenied(resultPermissionCode: Int)

}