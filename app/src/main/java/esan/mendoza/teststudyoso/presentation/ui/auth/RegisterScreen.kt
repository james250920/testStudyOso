package esan.mendoza.teststudyoso.presentation.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import esan.mendoza.teststudyoso.di.AppViewModelProvider
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel = viewModel(factory = AppViewModelProvider.UsuarioViewModelFactory)
) {
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var apellido by remember { mutableStateOf(TextFieldValue("")) }
    var correo by remember { mutableStateOf(TextFieldValue("")) }
    var contrasena by remember { mutableStateOf(TextFieldValue("")) }
    var fechaNacimiento by remember { mutableStateOf(TextFieldValue("")) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Observamos el error para mostrar mensaje
    val error by usuarioViewModel.error.collectAsState()
    // Observamos usuario para saber si se registró
    val usuarioRegistrado by usuarioViewModel.usuarioState.collectAsState()

    // Mostrar Snackbar si hay error
    LaunchedEffect(error) {
        error?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
            }
        }
    }

    // Navegar si el registro fue exitoso (usuarioRegistrado no es null)
    LaunchedEffect(usuarioRegistrado) {
        if (usuarioRegistrado != null) {
            navController.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Registro de Usuario") })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = fechaNacimiento,
                onValueChange = { fechaNacimiento = it },
                label = { Text("Fecha Nacimiento (yyyy-mm-dd)") },
                modifier = Modifier.fillMaxWidth()
            )
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
                    navController.navigate("login")
                    usuarioViewModel.registrar(
                        nombre = nombre.text.trim(),
                        apellido = apellido.text.trim(),
                        fechaNacimiento = fechaNacimiento.text.trim(),
                        correo = correo.text.trim(),
                        contrasena = contrasena.text
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // Agregar altura consistente
            ) {
                Text("Registrarse")
            }
        }

            TextButton(
                onClick = { navController.navigate("login") }
            ) {
                Text("¿Tienes cuenta? Iniciar Sesión")
            }
        }
    }



