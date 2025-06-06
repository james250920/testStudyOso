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
import androidx.compose.ui.Alignment

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

        EventosDelDiaCard()

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
private fun EventosDelDiaCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            Modifier
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
                    text = "Horario para hoy:",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { /* Acción al hacer clic */ },
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
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ){
                // Mostrar la fecha actual
                Button(
                    onClick = { /* Acción al hacer clic */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ){
                    Text(
                        text = "hoy " + LocalDate.now().format(
                            java.time.format.DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", java.util.Locale("es"))
                        ),
                        style = MaterialTheme.typography.labelSmall,

                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                // Mostrar la fecha del día siguiente
                Button(
                    onClick = { /* Acción al hacer clic */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        text = LocalDate.now().plusDays(1).format(
                            java.time.format.DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", java.util.Locale("es"))
                        ),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }

            }
            Spacer(modifier = Modifier.height(2.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(5) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,

                    ){
                        Column(
                            modifier = Modifier

                        ) {
                            Text(
                                text = "9:30 AM",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "12:15 PM",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.width(24.dp))
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                            Text(
                                text = "Marketing",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Row(

                            ) {
                                Text(
                                    text = "Presencial",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Aula: ",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "A-101",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                        }

                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp).background(color = MaterialTheme.colorScheme.onSurface))
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