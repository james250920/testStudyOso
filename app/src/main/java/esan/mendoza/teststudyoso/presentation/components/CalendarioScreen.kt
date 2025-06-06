package esan.mendoza.teststudyoso.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale

enum class CalendarMode {
    MONTH, WEEK, DAY
}

@Composable
fun CalendarioScreen(modifier: Modifier = Modifier) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var calendarMode by remember { mutableStateOf(CalendarMode.MONTH) }

    val eventosPorFecha = mapOf(
        LocalDate.now() to listOf("Revisión de código", "Reunión de proyecto"),
        LocalDate.now().plusDays(1) to emptyList()
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        CalendarHeader(
            currentMonth = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) },
            onModeChanged = { calendarMode = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (calendarMode) {
            CalendarMode.MONTH -> CalendarGrid(
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it },
                eventosPorFecha = eventosPorFecha
            )
            CalendarMode.WEEK -> SemanaLista(eventosPorFecha)
            CalendarMode.DAY -> EventosDiarios(
                selectedDate = selectedDate,
                eventos = eventosPorFecha[selectedDate].orEmpty()
            )
        }
    }
}

@Composable
private fun CalendarHeader(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onModeChanged: (CalendarMode) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Mes anterior")
        }
        Text(
            text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale("es"))),
            style = MaterialTheme.typography.titleLarge
        )
        IconButton(onClick = onNextMonth) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "Mes siguiente")
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { onModeChanged(CalendarMode.MONTH) }) {
            Text("Mes")
        }
        Button(onClick = { onModeChanged(CalendarMode.WEEK) }) {
            Text("Semana")
        }
        Button(onClick = { onModeChanged(CalendarMode.DAY) }) {
            Text("Día")
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val daysOfWeek = listOf("Dom","Lun", "Mar", "Mié", "Jue", "Vie", "Sáb")
        daysOfWeek.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun CalendarGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    eventosPorFecha: Map<LocalDate, List<String>>
) {
    val firstDayOfMonth = currentMonth.atDay(1)
    val lastDayOfMonth = currentMonth.atEndOfMonth()
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    val start = firstDayOfMonth.with(firstDayOfWeek)

    val daysInCalendar = mutableListOf<LocalDate>()
    var current = start
    while (current.isBefore(lastDayOfMonth.plusDays(1)) || daysInCalendar.size % 7 != 0) {
        daysInCalendar.add(current)
        current = current.plusDays(1)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
    ) {
        gridItems(daysInCalendar) { date ->
            DayCell(
                date = date,
                isSelected = date == selectedDate,
                isToday = date == LocalDate.now(), // Aquí destacamos el día actual
                isCurrentMonth = date.month == currentMonth.month,
                onDateSelected = onDateSelected
            )
        }
    }

    val eventosSeleccionados = eventosPorFecha[selectedDate].orEmpty()
    EventosDiarios(selectedDate = selectedDate, eventos = eventosSeleccionados)
}

@Composable
private fun SemanaLista(eventosPorFecha: Map<LocalDate, List<String>>) {
    val hoy = LocalDate.now()
    val diasAmostrar = buildList {
        var actual = hoy
        while (actual.dayOfWeek.value != 7) {
            add(actual)
            actual = actual.plusDays(1)
        }
        add(actual)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(diasAmostrar) { dia ->
            Text(
                text = dia.format(DateTimeFormatter.ofPattern("EEEE d 'de' MMMM", Locale("es"))),
                style = MaterialTheme.typography.titleMedium
            )
            val eventos = eventosPorFecha[dia].orEmpty()
            if (eventos.isEmpty()) {
                Text("No hay eventos")
            } else {
                eventos.forEach { evento ->
                    Text("\\- $evento")
                }
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
private fun DayCell(
    date: LocalDate,
    isSelected: Boolean,
    isToday: Boolean,  // Variable para destacar el día actual
    isCurrentMonth: Boolean,
    onDateSelected: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .background(
                when {
                    isSelected -> MaterialTheme.colorScheme.primary
                    isToday -> MaterialTheme.colorScheme.secondary // Día actual con color especial
                    else -> Color.Transparent
                }
            )
            .clickable { onDateSelected(date) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            color = when {
                isSelected -> MaterialTheme.colorScheme.onPrimary
                isToday -> MaterialTheme.colorScheme.onSecondary // Texto en color diferente para hoy
                !isCurrentMonth -> MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38f)
                else -> MaterialTheme.colorScheme.onBackground
            }
        )
    }
}

@Composable
private fun EventosDiarios(selectedDate: LocalDate, eventos: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Eventos para " + selectedDate.format(
                    DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", Locale("es"))
                ),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (eventos.isEmpty()) {
                Text("No hay eventos agendados")
            } else {
                eventos.forEach { evento ->
                    Text("\\* $evento")
                }
            }
        }
    }
}