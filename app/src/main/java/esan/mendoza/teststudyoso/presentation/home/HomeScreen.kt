package esan.mendoza.teststudyoso.presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import esan.mendoza.teststudyoso.navigation.DrawerContent
import esan.mendoza.teststudyoso.presentation.calificacion.AgregarCalificacionScreen
import esan.mendoza.teststudyoso.presentation.curso.AgregarCursosScreen
import esan.mendoza.teststudyoso.presentation.components.CalendarioScreen
import esan.mendoza.teststudyoso.presentation.calificacion.DetalleCalificacionesScreen
import esan.mendoza.teststudyoso.presentation.calificacion.ListCalificacionScreen
import esan.mendoza.teststudyoso.presentation.calificacion.SimuladorCalificacionesScreen
import esan.mendoza.teststudyoso.presentation.config.ConfiguracionScreen
import esan.mendoza.teststudyoso.presentation.components.DashboardScreen
import esan.mendoza.teststudyoso.presentation.tarea.ListaTareasScreen
import esan.mendoza.teststudyoso.presentation.components.MatrizEisenhowerScreen
import esan.mendoza.teststudyoso.presentation.usuario.PerfilScreen
import esan.mendoza.teststudyoso.presentation.components.PomodoroScreen
import esan.mendoza.teststudyoso.presentation.curso.DetalleCursoScreen
import esan.mendoza.teststudyoso.presentation.curso.ListCursoScreen
import esan.mendoza.teststudyoso.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    usuarioId: Int
    ) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedScreen by remember { mutableStateOf("Principal") }
    var isDarkTheme by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = if (isDarkTheme) {
            darkColorScheme(
                primary = Color(0xFF003366),
                onPrimary = Color.White,
                surface = Color(0xFF121212),
                background = Color(0xFF121212),
                onBackground = Color.White,
                onSurface = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF003366),
                onPrimary = Color.White,
                surface = Color.White,
                background = Color.White,
                onBackground = Color.Black,
                onSurface = Color.Black
            )
        }
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    DrawerContent(
                        selectedScreen = selectedScreen,
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
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { isDarkTheme = !isDarkTheme }) {
                                Icon(
                                    imageVector = if (isDarkTheme) Icons.Filled.WbSunny else Icons.Filled.Bedtime,
                                    contentDescription = "cambiar tema",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            IconButton(onClick = { selectedScreen = "Perfil" }) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "Perfil",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(30.dp)
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
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        NavigationBarItem(
                            selected = selectedScreen == "Dashboard",
                            onClick = { selectedScreen = "Dashboard" },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Analytics,
                                    contentDescription = "dashboard",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text("Dashboard") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF3355ff),
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.White
                            )
                        )

                        NavigationBarItem(
                            selected = selectedScreen == "Pomodoro",
                            onClick = { selectedScreen = "Pomodoro" },
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.book_clock),
                                    contentDescription = "pomodoro",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text("Pomodoro") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF3355ff),
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.White
                            )
                        )

                        NavigationBarItem(
                            selected = selectedScreen == "Principal",
                            onClick = { selectedScreen = "Principal" },
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = "home",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text("Inicio") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF3355ff),
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.White
                            )
                        )

                        NavigationBarItem(
                            selected = selectedScreen == "ListCalificaciones",
                            onClick = { selectedScreen = "ListCalificaciones" },
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.calificacion),
                                    contentDescription = "calificaciones",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text("Notas") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF3355ff),
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.White
                            )
                        )

                        NavigationBarItem(
                            selected = selectedScreen == "ListaTareas",
                            onClick = { selectedScreen = "ListaTareas" },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.AddCircleOutline,
                                    contentDescription = "lista_tareas",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text("Tareas") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF3355ff),
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.White
                            )
                        )
                    }
                },
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                when {
                    selectedScreen.startsWith("DetalleCalificaciones") -> {
                        val cursoId = selectedScreen.substringAfter("DetalleCalificaciones/").toIntOrNull()
                        if (cursoId != null) {
                            DetalleCalificacionesScreen(
                                modifier = Modifier.padding(innerPadding),
                                cursoId = cursoId,
                                onScreenSelected = { screen -> selectedScreen = screen }
                            )
                        } else {
                            ListCalificacionScreen(
                                modifier = Modifier.padding(innerPadding),
                                onScreenSelected = { screen -> selectedScreen = screen },
                                usuarioId = usuarioId
                            )
                        }
                    }
                    selectedScreen.startsWith("DetalleCurso") -> {
                        val cursoId = selectedScreen.substringAfter("DetalleCurso/").toIntOrNull()
                        if (cursoId != null) {
                            DetalleCursoScreen(
                                modifier = Modifier.padding(innerPadding),
                                onScreenSelected = { screen -> selectedScreen = screen },
                                cursoId = cursoId
                            )
                        } else {
                            // ID inválido, muestra lista de cursos o pantalla por defecto
                            ListCursoScreen(
                                modifier = Modifier.padding(innerPadding),
                                onScreenSelected = { screen -> selectedScreen = screen },
                                usuarioId = usuarioId
                            )
                        }
                    }
                    selectedScreen == "AgregarCalificacion" -> AgregarCalificacionScreen(
                        modifier = Modifier.padding(innerPadding),
                        onScreenSelected = { screen -> selectedScreen = screen },
                        usuarioId = usuarioId
                    )
                    selectedScreen == "AgregarCursos" -> AgregarCursosScreen(
                        modifier = Modifier.padding(innerPadding),
                        onScreenSelected = { screen -> selectedScreen = screen },
                        usuarioId = usuarioId
                    )

                    selectedScreen == "Calendario" -> CalendarioScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                    selectedScreen == "Configuración" -> ConfiguracionScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                    selectedScreen == "Dashboard" -> DashboardScreen(
                        modifier = Modifier.padding(innerPadding),
                        onScreenSelected = { screen -> selectedScreen = screen },
                        usuarioId = usuarioId
                    )
                    selectedScreen == "ListaTareas" -> ListaTareasScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                    selectedScreen == "ListCalificaciones" -> ListCalificacionScreen(
                        modifier = Modifier.padding(innerPadding),
                        onScreenSelected = { screen -> selectedScreen = screen },
                        usuarioId = usuarioId
                    )
                    selectedScreen == "lisCurso" -> ListCursoScreen(
                        modifier = Modifier.padding(innerPadding),
                        onScreenSelected = { screen -> selectedScreen = screen },
                        usuarioId = usuarioId
                    )
                    selectedScreen == "MatrizEisenhower" -> MatrizEisenhowerScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                    selectedScreen == "Perfil" -> PerfilScreen(
                        modifier = Modifier.padding(innerPadding),
                        onScreenSelected = { screen -> selectedScreen = screen }
                    )
                    selectedScreen == "Pomodoro" -> PomodoroScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                    selectedScreen == "Principal" -> PrincipalScreen(
                        modifier = Modifier.padding(innerPadding),
                        onScreenSelected = { screen -> selectedScreen = screen }
                    )
                    selectedScreen.startsWith("SimuladoCalificacion") -> {
                        val cursoId = selectedScreen.substringAfter("SimuladoCalificacion/").toIntOrNull()
                        if (cursoId != null) {
                            SimuladorCalificacionesScreen(
                                modifier = Modifier.padding(innerPadding),
                                cursoId = cursoId,
                                onScreenSelected = { screen -> selectedScreen = screen }
                            )
                        }
                    }
                }
            }
        }
    }
}




