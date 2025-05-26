package esan.mendoza.teststudyoso.domain

data class TipoPrueba(
    val id: Int = 0,
    val nombre: String,
    val numPruebas: Int,
    val cursoId: Int,
    val peso: Double
)