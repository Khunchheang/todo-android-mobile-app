package com.khunchheang.todo.ui.helper

import com.khunchheang.todo.util.AppConstants

class PermissionsFactory {

    fun getPermission(permissionCode: Int): Permission? {
        return if (permissionCode == AppConstants.PERMISSION_REQUEST_STORAGE) {
            StoragePermission()
        } else {
            null
        }
    }

}