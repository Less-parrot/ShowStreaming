package com.VNC.pasecodevnc.mediaProject

import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MediaProjectionRequest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mediaProjectionManager = getSystemService(MediaProjectionManager::class.java)
        var mediaProjection: MediaProjection

        val startMediaProjection = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                mediaProjection =
                    mediaProjectionManager.getMediaProjection(result.resultCode, result.data!!)
            }
        }


        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    Button(onClick = {
                        startMediaProjection.launch(mediaProjectionManager.createScreenCaptureIntent())
                    }
                    ) {
                        Text(text = "Grabar")
                    }

                    Button(onClick = {}) {
                        Text(text = "Parar de Grabar")
                    }
                }
            }

        }
    }
}

