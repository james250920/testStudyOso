package esan.mendoza.teststudyoso.presentation.tarea
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import esan.mendoza.teststudyoso.R
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(modifier: Modifier = Modifier,onScreenSelected: (String) -> Unit) {

    val cursos = listOf("Matemática", "Física", "Química")
    var expandidoCurso by remember { mutableStateOf(false) }
    var cursoSeleccionado by remember { mutableStateOf("") }
    val fechaActual = Calendar.getInstance()
    var showDatePicker by remember { mutableStateOf(false) }
    var fechaNacimiento by remember { mutableStateOf("") }
    var descripcionTarea by remember { mutableStateOf("") }

    var esImportante by remember { mutableStateOf<Boolean?>(null) }
    var esUrgente by remember { mutableStateOf<Boolean?>(null) }

    val contexto = LocalContext.current
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = null,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        }
    )
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val date = java.sql.Date(millis)
                        fechaNacimiento = TextFieldValue(date.toString()).text
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start

    ) {
        Image(
            painter = painterResource(id = R.drawable.addtaskme),
            contentDescription = "Logo Study Oso",
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Dropdown para cursos
        Text(text = "Curso", style = MaterialTheme.typography.bodyLarge)
        ExposedDropdownMenuBox(
            expanded = expandidoCurso,
            onExpandedChange = { expandidoCurso = !expandidoCurso }
        ) {
            OutlinedTextField(
                value = cursoSeleccionado,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoCurso) },
                label = { Text("Seleccionar") }
            )
            ExposedDropdownMenu(
                expanded = expandidoCurso,
                onDismissRequest = { expandidoCurso = false }
            ) {
                cursos.forEach { curso ->
                    DropdownMenuItem(
                        text = { Text(curso) },
                        onClick = {
                            cursoSeleccionado = curso
                            expandidoCurso = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Descripción de la tarea
        Text(text = "Descripción de la tarea", style = MaterialTheme.typography.bodyLarge)
        OutlinedTextField(
            value = descripcionTarea,
            onValueChange = { descripcionTarea = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(text = "Tarea de desarrollo móvil") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ¿Es importante?
        Text(text = "¿Es importante?", style = MaterialTheme.typography.bodyLarge)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val opcionesImportante = listOf("Sí" to true, "No" to false)
            opcionesImportante.forEach { (texto, valor) ->
                Row(
                    modifier = Modifier
                        .selectable(selected = esImportante == valor, onClick = { esImportante = valor })
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = esImportante == valor, onClick = { esImportante = valor })
                    Text(text = texto)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ¿Es urgente?
        Text(text = "¿Es urgente?", style = MaterialTheme.typography.bodyLarge)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val opcionesUrgente = listOf("Sí" to true, "No" to false)
            opcionesUrgente.forEach { (texto, valor) ->
                Row(
                    modifier = Modifier
                        .selectable(selected = esUrgente == valor, onClick = { esUrgente = valor })
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = esUrgente == valor, onClick = { esUrgente = valor })
                    Text(text = texto)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Fecha de vencimiento
        Text(text = "Fecha de vencimiento", style = MaterialTheme.typography.bodyLarge)
        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = { fechaNacimiento = it },
            label = { Text("Fecha de Nacimiento") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
               onScreenSelected(
                    "ListTaskScreen"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Guardar")
        }
    }
}
