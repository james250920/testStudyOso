package esan.mendoza.teststudyoso.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToPhotos
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import esan.mendoza.teststudyoso.R
import java.time.LocalDate

// Constante para el color de los íconos
private val IconColor = Color(0xFF3355ff)

// Función auxiliar para obtener los eventos del día
private fun obtenerEventosPorFecha(): Map<LocalDate, List<String>> {
    return mapOf(
        LocalDate.now() to listOf("Revisión de código", "Reunión de proyecto"),
        LocalDate.now().plusDays(1) to emptyList()
    )
}

@Composable
fun PrincipalScreen(
    modifier: Modifier = Modifier,
    onScreenSelected: (String) -> Unit
) {
    val eventosPorFecha = obtenerEventosPorFecha()
    val fechaHoy = LocalDate.now()
    val eventosHoy = eventosPorFecha[fechaHoy].orEmpty()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card para mostrar los eventos del día
        EventosDelDiaCard(eventosHoy)

        Spacer(modifier = Modifier.height(32.dp))

        // Imagen de fondo
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
private fun EventosDelDiaCard(eventosHoy: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Asignaciones para hoy:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (eventosHoy.isEmpty()) {
                Text(
                    text = "No hay eventos para hoy",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                eventosHoy.forEach { evento ->
                    Text(
                        text = "- $evento",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
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
            .padding(vertical = 16.dp),
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
            .padding(vertical = 16.dp).padding(16.dp),
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