package esan.mendoza.teststudyoso.presentation.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel
) {
    var correo by remember { mutableStateOf(TextFieldValue("")) }
    var contrasena by remember { mutableStateOf(TextFieldValue("")) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val usuarioRegistrado by usuarioViewModel.usuarioState.collectAsState()
    val error by usuarioViewModel.error.collectAsState()

    // Mostrar Snackbar si hay error
    LaunchedEffect(error) {
        error?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
            }
        }
    }

    // Navegar cuando el usuario está autenticado
    LaunchedEffect(usuarioRegistrado) {
        if (usuarioRegistrado != null) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Iniciar Sesión") })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo Electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // No se necesita trim() en contraseña
                    usuarioViewModel.iniciarSesion(
                        correo = correo.text.trim(),
                        contrasena = contrasena.text // Sin trim()
                    )
                },
                enabled = correo.text.isNotBlank() && contrasena.text.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Iniciar Sesión")
            }

            TextButton(
                onClick = { navController.navigate("register") }
            ) {
                Text("¿No tienes cuenta? Regístrate")
            }
        }
    }
}
