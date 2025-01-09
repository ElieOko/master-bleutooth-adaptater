package com.master.masteradaptaterbleutooth.core

import android.Manifest.permission.BLUETOOTH
import android.Manifest.permission.BLUETOOTH_ADMIN
import android.Manifest.permission.BLUETOOTH_ADVERTISE
import android.Manifest.permission.BLUETOOTH_SCAN
import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.viewmodel.compose.viewModel
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.master.masteradaptaterbleutooth.core.permission.PermissionAndroid
import com.master.masteradaptaterbleutooth.core.permission.PermissionAndroid.permissionsToRequest
import com.master.masteradaptaterbleutooth.core.permission.PermissionDialog
import com.master.masteradaptaterbleutooth.core.permission.PermissionScreenState
import com.master.masteradaptaterbleutooth.core.permission.doPermissionAction
import com.master.masteradaptaterbleutooth.core.permission.getState
import com.master.masteradaptaterbleutooth.core.permission.isPermissionGranted
import com.master.masteradaptaterbleutooth.core.ui.theme.MasterAdaptaterBleutoothTheme
import com.master.masteradaptaterbleutooth.core.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.S)
class MainActivity : ComponentActivity() {



    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(35)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<MainViewModel>()
            val dialogQueue = viewModel.visiblePermissionDialogQueue
            val context = LocalContext.current

//            val permissionRequest: ActivityResultLauncher<Array<String>> =
//                registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
//                    doPermissionAction(context)
//                }
//            val permissionScreenState: MutableState<PermissionScreenState> by lazy {
//                mutableStateOf(getState(context))
//            }
            val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = { perms ->
                    permissionsToRequest.forEach { permission ->
                        viewModel.onPermissionResult(
                            permission = permission,
                            isGranted = perms[permission] == true
                        )
                    }
                }
            )
//            val permissionLauncher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.RequestPermission()
//            ) { isGranted ->
//                hasNotificationPermission = isGranted
//                if (hasNotificationPermission && !isServiceRunning) {
//                    screenRecordLauncher.launch(
//                        mediaProjectionManager.createScreenCaptureIntent()
//                    )
//                }
//            }
            MasterAdaptaterBleutoothTheme {
                // Check to see if the Bluetooth classic feature is available.
                val bluetoothAvailable: Boolean = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)
                // Check to see if the BLE feature is available.
                val bluetoothLEAvailable: Boolean = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
                Scaffold(modifier = Modifier.fillMaxSize()) {
//                    Greeting(
//                        name = "Android BLE $bluetoothLEAvailable|B classic $bluetoothAvailable",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            cameraPermissionResultLauncher.launch(
                                Manifest.permission.CAMERA
                            )
                        }) {
                            Text(text = "Request one permission")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            multiplePermissionResultLauncher.launch(permissionsToRequest)
                        }) {
                            Text(text = "Request multiple permission")
                        }
                    }

                    dialogQueue
                        .reversed()
                        .forEach { permission ->
                            PermissionDialog(
                                permissionTextProvider = when (permission) {
                                    Manifest.permission.CAMERA -> {
                                        CameraPermissionTextProvider()
                                    }
                                    Manifest.permission.RECORD_AUDIO -> {
                                        RecordAudioPermissionTextProvider()
                                    }
                                    Manifest.permission.CALL_PHONE -> {
                                        PhoneCallPermissionTextProvider()
                                    }
                                    else -> return@forEach
                                },
                                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                                    permission
                                ),
                                onDismiss = viewModel::dismissDialog,
                                onOkClick = {
                                    viewModel.dismissDialog()
                                    multiplePermissionResultLauncher.launch(
                                        arrayOf(permission)
                                    )
                                },
                                onGoToAppSettingsClick = ::openAppSettings
                            )
                        }
                }
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MasterAdaptaterBleutoothTheme {
        Greeting("Android")
    }
}

