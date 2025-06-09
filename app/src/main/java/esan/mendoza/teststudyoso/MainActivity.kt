package esan.mendoza.teststudyoso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import esan.mendoza.teststudyoso.di.PermissionHelper
import esan.mendoza.teststudyoso.di.WorkManagerHelper
import esan.mendoza.teststudyoso.navigation.NavegacionApp
import esan.mendoza.teststudyoso.ui.theme.TestStudyOsoTheme

class MainActivity : ComponentActivity() {
    private lateinit var workManagerHelper: WorkManagerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Solicitar permiso de notificaciones
        if (!PermissionHelper.checkNotificationPermission(this)) {
            PermissionHelper.requestNotificationPermission(this) { isGranted ->
                if (isGranted) {
                    // Inicializar WorkManager ahora que tenemos permiso
                    setupWorkManager()
                }
            }
        } else {
            // Ya tenemos permiso, inicializar WorkManager
            setupWorkManager()
        }

        setContent {
            TestStudyOsoTheme {
                val textInput = remember { mutableStateOf("") }
                NavegacionApp()
            }
        }
    }

    private fun setupWorkManager() {
        // Inicializar WorkManagerHelper
        workManagerHelper = WorkManagerHelper(this)
        // Iniciar verificación de tareas vencidas
        workManagerHelper.verificarTareasVencidas()
    }

    companion object {
        const val MESSAGE_STATUS = "message_status"
    }
}
