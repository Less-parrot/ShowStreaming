package com.VNC.pasecodevnc.mediaProject

import android.media.MediaRecorder
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MediaProjectionRequest : ComponentActivity() {
    private lateinit var mediaRecorder: MediaRecorder


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mediaProjectionManager = getSystemService(MediaProjectionManager::class.java)
        val startMediaProjection = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                Toast.makeText(applicationContext, "CONCEDIDO", Toast.LENGTH_SHORT).show()
                //mediaProjection = mediaProjectionManager.getMediaProjection(result.resultCode, result.data!!)
                startRecording()
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

                    Button(onClick = {
                        stopRecording()
                    }) {
                        Text(text = "Parar de Grabar")
                    }
                }
            }

        }
    }


    private fun startRecording() {
        mediaRecorder = MediaRecorder()

         mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        //mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setOutputFile(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
                .toString() + "/video.mp4"
        )
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        //mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)

        mediaRecorder.prepare()
        mediaRecorder.start()
    }

    private fun stopRecording() {
        // Detiene la grabación de la pantalla.
        mediaRecorder.stop()
        mediaRecorder.release()
    }
}



