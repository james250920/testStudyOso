package esan.mendoza.teststudyoso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import esan.mendoza.teststudyoso.navigation.NavegacionApp
import esan.mendoza.teststudyoso.ui.theme.TestStudyOsoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestStudyOsoTheme {

                    NavegacionApp()

            }
        }
    }
}
