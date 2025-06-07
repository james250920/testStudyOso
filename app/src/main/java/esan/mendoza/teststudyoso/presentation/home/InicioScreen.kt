package esan.mendoza.teststudyoso.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToPhotos
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import esan.mendoza.teststudyoso.R
import java.time.LocalDate
import kotlin.text.format
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import esan.mendoza.teststudyoso.ViewModel.Horario.HorarioViewModel
import esan.mendoza.teststudyoso.ViewModel.Horario.HorarioViewModelFactory
import esan.mendoza.teststudyoso.ViewModel.curso.CursoViewModel
import esan.mendoza.teststudyoso.ViewModel.curso.CursoViewModelFactory
import esan.mendoza.teststudyoso.data.db.AppDatabase
import esan.mendoza.teststudyoso.data.entities.Curso
import esan.mendoza.teststudyoso.data.repositories.CursoRepository
import esan.mendoza.teststudyoso.data.repositories.HorarioRepository
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.*
import java.util.Locale

// Constante para el color de los íconos
private val IconColor = Color(0xFF3355ff)


@Composable
fun PrincipalScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EventosDelDiaCard(
            modifier = Modifier.fillMaxWidth(),
            onViewAllClick = { onScreenSelected("Calendario") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.study),
            contentDescription = "Imagen de fondo",
            modifier = Modifier
                .fillMaxWidth()
                .height(165.dp)
                .padding(bottom = 16.dp)
        )

        // Menú de opciones
        MenuOpciones(onScreenSelected)
    }
}

@Composable
private fun EventosDelDiaCard(
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit = {},
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }

    // Repositorios y ViewModels
    val horarioRepository = remember { HorarioRepository(db.HorarioDao()) }
    val horarioViewModel: HorarioViewModel = viewModel(factory = HorarioViewModelFactory(horarioRepository))
    val horarios by horarioViewModel.horarios.collectAsState(initial = emptyList())

    val cursoRepository = remember { CursoRepository(db.CursoDao()) }
    val cursoViewModel: CursoViewModel = viewModel(factory = CursoViewModelFactory(cursoRepository))

    // Estado para día seleccionado
    var selectedDay by remember { mutableStateOf(0) } // 0 = hoy, 1 = mañana

    // Obtener fechas
    val hoy = LocalDate.now()
    val mañana = hoy.plusDays(1)
    val fechaSeleccionada = if (selectedDay == 0) hoy else mañana

    // Mapeo de días de la semana
    val diaMapper = mapOf(
        "MONDAY" to "LUNES",
        "TUESDAY" to "MARTES",
        "WEDNESDAY" to "MIÉRCOLES",
        "THURSDAY" to "JUEVES",
        "FRIDAY" to "VIERNES",
        "SATURDAY" to "SÁBADO",
        "SUNDAY" to "DOMINGO"
    )

    // Obtener día en español
    val diaEn = fechaSeleccionada.dayOfWeek.name
    val diaEs = diaMapper[diaEn] ?: diaEn

    // Filtrar horarios para el día seleccionado
    val horariosFiltrados = horarios.filter {
        it.diaSemana.equals(diaEs, ignoreCase = true)
    }

    // Estado para almacenar cursos
    val cursosMap = remember { mutableStateMapOf<Int, Curso?>() }

    // Cargar todos los horarios al iniciar
    LaunchedEffect(Unit) {
        horarioViewModel.cargarTodosLosHorarios()
    }

    // Cargar cursos para los horarios filtrados
    LaunchedEffect(horariosFiltrados) {
        horariosFiltrados.forEach { horario ->
            if (!cursosMap.containsKey(horario.idCurso)) {
                val curso = cursoViewModel.getCursoById(horario.idCurso)
                cursosMap[horario.idCurso] = curso
            }
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "Eventos del día",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Horario para ${if (selectedDay == 0) "hoy" else "mañana"}:",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = onViewAllClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "ver todos",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Ver todos los eventos",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                // Botón Hoy
                Button(
                    onClick = { selectedDay = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedDay == 0)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.primaryContainer,
                        contentColor = if (selectedDay == 0)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Hoy, " + hoy.format(
                            DateTimeFormatter.ofPattern("d 'de' MMMM", Locale("pe"))
                        ),
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Botón Mañana
                Button(
                    onClick = { selectedDay = 1 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedDay == 1)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.primaryContainer,
                        contentColor = if (selectedDay == 1)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Mañana, " + mañana.format(
                            DateTimeFormatter.ofPattern("d 'de' MMMM", Locale("pe"))
                        ),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (horariosFiltrados.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No hay horarios para ${if (selectedDay == 0) "hoy" else "mañana"}",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                        TextButton(onClick = onViewAllClick) {
                            Text("Ver calendario completo")
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(horariosFiltrados.size) { index ->
                        val horario = horariosFiltrados[index]
                        val curso = cursosMap[horario.idCurso]

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = curso?.color?.let { colorString ->
                                    try {
                                        Color(android.graphics.Color.parseColor(colorString))
                                    } catch (e: Exception) {
                                        MaterialTheme.colorScheme.surface
                                    }
                                } ?: MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Horario
                                Column(
                                    modifier = Modifier.width(90.dp)
                                ) {
                                    Text(
                                        text = horario.horaInicio,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = horario.horaFin,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                // Información del curso
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = curso?.nombreCurso ?: "Cargando...",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Modalidad: ${curso?.aula ?: "Desconocida"}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    Text(
                                        text = "Aula: ${horario.aula}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }

                        if (index < horariosFiltrados.size - 1) {
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuOpciones(onScreenSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MenuItem(
            icon = Icons.Filled.AddToPhotos,
            text = "Cursos",
            onClick = { onScreenSelected("lisCurso") }
        )
        MenuItem(
            icon = Icons.Filled.CalendarMonth,
            text = "Calendario",
            onClick = { onScreenSelected("Calendario") }
        )
        MenuItem(
            icon = Icons.Filled.FormatListNumbered,
            text = "Tareas",
            onClick = { onScreenSelected("ListTaskScreen") }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp).padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MenuItem(
            icon = Icons.Filled.Timer,
            text = "Pomodoro",
            onClick = { onScreenSelected("Pomodoro") }
        )
        MenuItem(
            icon = Icons.Filled.Dataset,
            text = "Matriz Eisenhower",
            onClick = { onScreenSelected("MatrizEisenhower") }
        )
        MenuItem(
            icon = Icons.Filled.AllInclusive,
            text = "...",
            onClick = { "" }
        )
    }
}

@Composable
fun MenuItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(65.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = IconColor,
                modifier = Modifier.size(60.dp)
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}