package esan.mendoza.teststudyoso.domain.model

data class TipoPrueba(
    val id: Int = 0,
    val id_curso: Int = 0,
    val nombre_tipo: String = "",
    val cantidad_pruebas: Int = 0,
    val peso_total: Double = 0.00,
)