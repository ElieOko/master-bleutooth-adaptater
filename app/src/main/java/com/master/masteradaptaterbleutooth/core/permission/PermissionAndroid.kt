package com.master.masteradaptaterbleutooth.core.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * @author Elie Oko
 */
object PermissionAndroid {
    const val BLUETOOTH = Manifest.permission.BLUETOOTH
    @RequiresApi(Build.VERSION_CODES.S)
    const val BLUETOOTH_CONNECT = Manifest.permission.BLUETOOTH_CONNECT
    const val BLUETOOTH_ADMIN = Manifest.permission.BLUETOOTH_ADMIN
    @RequiresApi(Build.VERSION_CODES.S)
    const val BLUETOOTH_SCAN = Manifest.permission.BLUETOOTH_SCAN
    @RequiresApi(Build.VERSION_CODES.S)
    const val BLUETOOTH_ADVERTISE = Manifest.permission.BLUETOOTH_ADVERTISE
    const val ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE
    const val VIBRATE = Manifest.permission.VIBRATE
    const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    const val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    @RequiresApi(Build.VERSION_CODES.S)
    val permissionsToRequest = arrayOf(BLUETOOTH,BLUETOOTH_CONNECT,BLUETOOTH_ADMIN,BLUETOOTH_ADVERTISE,BLUETOOTH_SCAN,ACCESS_NETWORK_STATE,VIBRATE,ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION)
}