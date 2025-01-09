package com.master.masteradaptaterbleutooth.core.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

private fun isPermissionGranted(name: String, context: Context) = ContextCompat.checkSelfPermission(
    context, name
) == PackageManager.PERMISSION_GRANTED