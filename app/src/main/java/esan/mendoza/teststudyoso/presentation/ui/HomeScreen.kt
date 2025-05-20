package esan.mendoza.teststudyoso.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import esan.mendoza.teststudyoso.R
import kotlinx.coroutines.launch
import esan.mendoza.teststudyoso.navigation.DrawerContent
import esan.mendoza.teststudyoso.presentation.viewModel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedScreen by remember { mutableStateOf("Principal") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    selectedScreen = selectedScreen, // Pasar selectedScreen
                    onOptionSelected = { option ->
                        when (option) {
                            "Perfil" -> selectedScreen = "Perfil"
                            "Dashboard" -> selectedScreen = "Dashboard"
                            "Configuración" -> selectedScreen = "Configuración"
                            "Pomodoro" -> selectedScreen = "Pomodoro"
                            "Cerrar Sesión" -> navController.navigate("login") { popUpTo(0) }
                        }
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("StudyOsO", style = MaterialTheme.typography.titleLarge) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menú",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { selectedScreen = "Perfil" }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Perfil",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.height(80.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { selectedScreen = "Dashboard" }, modifier = Modifier.size(48.dp)) {
                            Icon(
                                imageVector = Icons.Filled.Analytics,
                                contentDescription = "dashboard",
                                tint = if (selectedScreen == "Dashboard") Color(0xFFFFA500) else Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = { selectedScreen = "Pomodoro" }, modifier = Modifier.size(48.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.book_clock),
                                contentDescription = "pomodoro",
                                tint = if (selectedScreen == "Pomodoro") Color(0xFFFFA500) else Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = { selectedScreen = "Principal" }, modifier = Modifier.size(48.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "home",
                                tint = if (selectedScreen == "Principal") Color(0xFFFFA500) else Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = { selectedScreen = "Calificaciones" }, modifier = Modifier.size(48.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.calificacion),
                                contentDescription = "calificaciones",
                                tint = if (selectedScreen == "Calificaciones") Color(0xFFFFA500) else Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        IconButton(onClick = { selectedScreen = "ListaTareas" }, modifier = Modifier.size(48.dp)) {
                            Icon(
                                imageVector = Icons.Filled.AddCircleOutline,
                                contentDescription = "lista_tareas",
                                tint = if (selectedScreen == "ListaTareas") Color(0xFFFFA500) else Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            when (selectedScreen) {
                "Principal" -> PrincipalScreen(
                    modifier = Modifier.padding(innerPadding),
                    onScreenSelected = { screen -> selectedScreen = screen }
                )
                "Dashboard" -> DashboardScreen(
                    modifier = Modifier.padding(innerPadding),
                    onScreenSelected = { screen -> selectedScreen = screen }
                )
                "Perfil" -> PerfilScreen(Modifier.padding(innerPadding))
                "Configuración" -> ConfiguracionScreen(Modifier.padding(innerPadding))
                "Pomodoro" -> PomodoroScreen(Modifier.padding(innerPadding))
                "AgregarCursos" -> AgregarCursosScreen(Modifier.padding(innerPadding))
                "Calificaciones" -> CalificacionesScreen(Modifier.padding(innerPadding))
                "ListaTareas" -> ListaTareasScreen(Modifier.padding(innerPadding))
                "MatrizEisenhower" -> MatrizEisenhowerScreen(Modifier.padding(innerPadding))
                "Calendario" -> CalendarioScreen(Modifier.padding(innerPadding))
            }
        }
    }
}




