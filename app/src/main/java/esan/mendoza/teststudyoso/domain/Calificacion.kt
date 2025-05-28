package esan.mendoza.teststudyoso.domain

data class Calificacion(
    val id: Int = 0,
    val cursoId: Int,
    val tipoPruebaId: Int,
    val numeroPrueba: Int,
    val calificacionObtenida: Double?
)