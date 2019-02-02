package com.khunchheang.todo.ui.helper

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import com.khunchheang.todo.ui.listener.PermissionListener
import com.khunchheang.todo.util.AppConstants

class StoragePermission:Permission {

    override fun requestPermission(context: Context, permissionListener: PermissionListener) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionListener.startRequestPermission(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                AppConstants.PERMISSION_REQUEST_STORAGE
            )
        } else {
            permissionListener.onPermissionGranted(AppConstants.PERMISSION_REQUEST_STORAGE)
        }
    }

    override fun permissionResult(grantResults: IntArray, permissionListener: PermissionListener) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionListener.onPermissionGranted(AppConstants.PERMISSION_REQUEST_STORAGE)
        } else {
            permissionListener.onPermissionDenied(AppConstants.PERMISSION_REQUEST_STORAGE)
        }
    }

    override fun isPermissionGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

}