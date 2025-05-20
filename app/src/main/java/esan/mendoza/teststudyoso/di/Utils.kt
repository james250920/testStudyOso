package esan.mendoza.teststudyoso.di

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun parseFechaNacimiento(fechaString: String): Date? {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.isLenient = false // Evita fechas inválidas
        dateFormat.parse(fechaString)
    } catch (e: Exception) {
        null
    }
}