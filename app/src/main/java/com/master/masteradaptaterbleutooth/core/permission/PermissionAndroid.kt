package com.master.masteradaptaterbleutooth.core.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * @author Elie Oko
 */
object PermissionAndroid {
    private const val BLUETOOTH = Manifest.permission.BLUETOOTH
    @RequiresApi(Build.VERSION_CODES.S)
    private const val BLUETOOTH_CONNECT = Manifest.permission.BLUETOOTH_CONNECT
    private const val BLUETOOTH_ADMIN = Manifest.permission.BLUETOOTH_ADMIN
    @RequiresApi(Build.VERSION_CODES.S)
    private const val BLUETOOTH_SCAN = Manifest.permission.BLUETOOTH_SCAN
    @RequiresApi(Build.VERSION_CODES.S)
    private const val BLUETOOTH_ADVERTISE = Manifest.permission.BLUETOOTH_ADVERTISE
    private const val ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE
    private const val VIBRATE = Manifest.permission.VIBRATE
    private const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private const val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
}