package com.master.masteradaptaterbleutooth.core.permission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

/**
 * @author Elie Oko
 */
fun isPermissionGranted(name: String, context: Context):Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
    ContextCompat.checkSelfPermission(
    context, name
) == PackageManager.PERMISSION_GRANTED else true


@RequiresApi(Build.VERSION_CODES.S)
fun doPermissionAction(context: Context): PermissionScreenState {
    var newState = getState(context)
    val bleutooth = isPermissionGranted(PermissionAndroid.BLUETOOTH,context)
    val bleutoothScan = isPermissionGranted(PermissionAndroid.BLUETOOTH_SCAN,context)
    val bleutoothAdmin = isPermissionGranted(PermissionAndroid.BLUETOOTH_ADMIN,context)
    val bleutoothAdvertise = isPermissionGranted(PermissionAndroid.BLUETOOTH_ADVERTISE,context)
    val bleutoothConnect = isPermissionGranted(PermissionAndroid.BLUETOOTH_CONNECT,context)
    val accessNetworkState = isPermissionGranted(PermissionAndroid.ACCESS_NETWORK_STATE,context)
    val vibrate = isPermissionGranted(PermissionAndroid.VIBRATE,context)
    when {
        bleutooth && bleutoothScan && bleutoothAdmin && bleutoothAdvertise && bleutoothConnect -> Toast.makeText(context,"Connected Bleutooth",Toast.LENGTH_LONG).show()
        vibrate -> Toast.makeText(context,"Grant Vibrate",Toast.LENGTH_LONG).show()
        accessNetworkState -> Toast.makeText(context,"Access Network State ACESS",Toast.LENGTH_LONG).show()
        else -> newState = newState.copy(
            errorText = "Cannot record video without camera permission"
        )
    }
    return newState
}
@RequiresApi(Build.VERSION_CODES.S)
fun getState(context: Context): PermissionScreenState {
    val bleutooth = isPermissionGranted(PermissionAndroid.BLUETOOTH,context)
    val bleutoothScan = isPermissionGranted(PermissionAndroid.BLUETOOTH_SCAN,context)
    val bleutoothAdmin = isPermissionGranted(PermissionAndroid.BLUETOOTH_ADMIN,context)
    val bleutoothAdvertise = isPermissionGranted(PermissionAndroid.BLUETOOTH_ADVERTISE,context)
    val bleutoothConnect = isPermissionGranted(PermissionAndroid.BLUETOOTH_CONNECT,context)
    val accessNetworkState = isPermissionGranted(PermissionAndroid.ACCESS_NETWORK_STATE,context)
    val vibrate = isPermissionGranted(PermissionAndroid.VIBRATE,context)
    //val vibrate = isPermissionGranted(PermissionAndroid.VIBRATE,context)
    return when {
        bleutooth and bleutoothScan and bleutoothAdmin and  bleutoothAdvertise and  bleutoothConnect and accessNetworkState and  vibrate -> {
            PermissionScreenState(
                title = "Start now", buttonText = "Bleutooth"
            )
        }
        else -> {
            PermissionScreenState(
                title = "Bleutooth Capteur", buttonText = if (!bleutooth and !bleutoothScan and !bleutoothAdmin and  !bleutoothAdvertise and  !bleutoothConnect and !accessNetworkState and  !vibrate) {
                    "Grant permissions"
                } else {
                    "Connected"
                },
                errorText = if (bleutooth and !bleutoothScan and !bleutoothAdmin and  !bleutoothAdvertise and  !bleutoothConnect and !accessNetworkState and  !vibrate) {
                    "Missing record audio permission"
                } else {
                    ""
                }
            )
        }
    }
}
