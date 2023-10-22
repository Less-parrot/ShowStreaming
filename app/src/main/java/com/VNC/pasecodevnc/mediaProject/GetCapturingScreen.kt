package com.VNC.pasecodevnc.mediaProject

import android.Manifest
import android.content.Context
import android.media.projection.MediaProjectionManager
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@RequiresApi(34)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetPermissions() {
    val permissionsState = rememberMultiplePermissionsState(permissions = listOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.RECEIVE_BOOT_COMPLETED,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.FOREGROUND_SERVICE,
        Manifest.permission.WAKE_LOCK,
        Manifest.permission.POST_NOTIFICATIONS,
    )
    )

    LaunchedEffect(true){
        permissionsState.launchMultiplePermissionRequest()
    }

    if (permissionsState.allPermissionsGranted){
        Text(text = "permisos consedido")
    }else {
        Text(text = "permiso denegado")
    }
}


@Composable
fun GetCapturingScreen(){
    val context = LocalContext.current
}

