@file:Suppress("UNUSED_EXPRESSION")

package com.VNC.pasecodevnc

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.VNC.pasecodevnc.mediaProject.GetPermissions
import com.VNC.pasecodevnc.ui.theme.PaseCodeVNCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaseCodeVNCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    val context = LocalContext.current
    var state by remember { mutableStateOf(false) }

        
    Box (contentAlignment = Alignment.Center) {
        Button(onClick = { state = true }) {
            Text(text = "Mostrar Menú")
        }
    }

    if (state){
        //OpenFileManager(context)
        GetPermissions()
    }
}


fun OpenFileManager(context: Context) {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    intent.type = "*/*"  // Puedes ajustar el tipo de archivo según tus necesidades
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // Manejar la excepción si no se encuentra una aplicación de administración de archivos.
        // Puedes mostrar un mensaje al usuario para instalar una aplicación de administración de archivos.
        Toast.makeText(
            context, "NO SE PUDO ENTRAR A EL ADMINISTRADOR DE ARCHIVOS", Toast.LENGTH_SHORT
        ).show()
    }
}
