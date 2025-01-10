package com.master.masteradaptaterbleutooth.core

import android.Manifest.permission.BLUETOOTH
import android.Manifest.permission.BLUETOOTH_ADMIN
import android.Manifest.permission.BLUETOOTH_ADVERTISE
import android.Manifest.permission.BLUETOOTH_SCAN
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
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

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.master.masteradaptaterbleutooth.core.permission.PermissionAndroid.BLUETOOTH_CONNECT
import com.master.masteradaptaterbleutooth.core.ui.theme.MasterAdaptaterBleutoothTheme
import com.master.masteradaptaterbleutooth.core.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.S)
class MainActivity : ComponentActivity() {

    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }
    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(35)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val enableBluetoothLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { /* Not needed */ }

            val permissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { perms ->
                val canEnableBluetooth = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    perms[BLUETOOTH_CONNECT] == true
                } else true

                if(canEnableBluetooth && !isBluetoothEnabled) {
                    enableBluetoothLauncher.launch(
                        Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    )
                }
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                permissionLauncher.launch(
                    arrayOf(BLUETOOTH_SCAN, BLUETOOTH_CONNECT)
                )
            }
            MasterAdaptaterBleutoothTheme {
                // Check to see if the Bluetooth classic feature is available.
                val bluetoothAvailable: Boolean =
                    packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)
                // Check to see if the BLE feature is available.
                val bluetoothLEAvailable: Boolean =
                    packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting(
                        name = "Android BLE $bluetoothLEAvailable|B classic $bluetoothAvailable")

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


@Composable
fun GreetingPreview() {
    MasterAdaptaterBleutoothTheme {
        Greeting("Android")
    }
}

