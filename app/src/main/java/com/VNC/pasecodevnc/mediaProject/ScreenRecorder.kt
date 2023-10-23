package com.VNC.pasecodevnc.mediaProject

import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text

class MainActivity2 : ComponentActivity() {

    private lateinit var mediaRecorder: MediaRecorder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Button(onClick = {
                startRecording()

            })
            {
                Text(text = "go")
            }
            Button(onClick = {
                stopRecording()

            }
            ) {
                Text(text = "stop")
            }
        }
    }

    private fun startRecording() {
        // Crea un objeto MediaRecorder para grabar la pantalla.
        mediaRecorder = MediaRecorder()

        // Configura el objeto MediaRecorder para grabar el audio y el video de la pantalla.
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setOutputFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString() + "/screencast.mp4")
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

        // Comienza la grabación de la pantalla.
        mediaRecorder.prepare()
        mediaRecorder.start()
    }

    private fun stopRecording() {
        // Detiene la grabación de la pantalla.
        mediaRecorder.stop()
        mediaRecorder.release()
    }
}
