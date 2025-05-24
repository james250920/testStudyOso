package esan.mendoza.teststudyoso.domain.model

import esan.mendoza.teststudyoso.data.entity.TipoPrueba

object  Cursos {
    val id_curso: Int = 0
    val id_usuario: Int = 0
    val nombre_curso: String = ""
    val color: String = ""
    val profesor: String = ""
    val aula: String = ""
    val creditos: Int = 0
}


data class Curso(
    val id: Int = 0,
    val nombreCurso: String,
    val profesor: String,
    val aula: String,
    val pruebas: List<TipoPrueba> = emptyList()
)