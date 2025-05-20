package esan.mendoza.teststudyoso.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import esan.mendoza.teststudyoso.data.entity.Usuario


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel: UsuarioViewModel, navController: NavHostController) {
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var apellido by remember { mutableStateOf(TextFieldValue("")) }
    var correo by remember { mutableStateOf(TextFieldValue("")) }
    var contrasena by remember { mutableStateOf(TextFieldValue("")) }
    var fechaNacimiento by remember { mutableStateOf(TextFieldValue("")) }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Usuario") }
            )
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
            OutlinedTextField(
                value = fechaNacimiento,
                onValueChange = { /* Solo editable vía DatePicker */ },
                label = { Text("Fecha de Nacimiento (yyyy-MM-dd)") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
            )
            Button(
                onClick = {

                    viewModel.insert(
                        Usuario(
                            nombre = nombre.text,
                            apellido = apellido.text,
                            correo = correo.text,
                            contrasena = contrasena.text,
                            fechaNacimiento = fechaNacimiento.text
                        )
                    )
                    navController.navigate("course_list") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Registrarse")
            }
        }
    }
}

