package esan.mendoza.teststudyoso.di

import esan.mendoza.teststudyoso.data.dao.TipoPruebaDao

object ValidationUtils {
    private val validDays = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    private val timeRegex = Regex("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")
    private val validEstados = listOf("Pendiente", "Completada")

    fun isValidDiaSemana(dia: String): Boolean {
        return validDays.contains(dia)
    }

    fun isValidTimeFormat(time: String): Boolean {
        return time.matches(timeRegex)
    }

    fun isValidEstado(estado: String): Boolean {
        return validEstados.contains(estado)
    }

    suspend fun isValidNumeroPrueba(
        numeroPrueba: Int,
        idTipoPrueba: Int,
        tipoPruebaDao: TipoPruebaDao
    ): Boolean {
        val tipoPrueba = tipoPruebaDao.getTipoPruebaById(idTipoPrueba)
        return tipoPrueba != null && numeroPrueba <= tipoPrueba.cantidadPruebas
    }
}