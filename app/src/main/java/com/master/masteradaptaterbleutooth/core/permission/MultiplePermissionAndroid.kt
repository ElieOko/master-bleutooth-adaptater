package com.master.masteradaptaterbleutooth.core.permission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

/**
 * @author Elie Oko
 */
private fun isPermissionGranted(name: String, context: Context):Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
    ContextCompat.checkSelfPermission(
    context, name
) == PackageManager.PERMISSION_GRANTED else true