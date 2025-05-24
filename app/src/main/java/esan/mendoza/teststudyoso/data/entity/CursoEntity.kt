package esan.mendoza.teststudyoso.data.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Entity(
    tableName = "Curso",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["idUsuario"],
            childColumns = ["idUsuario"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Curso(
    @PrimaryKey(autoGenerate = true) val idCurso: Int = 0,
    val nombreCurso: String,
    val color: String?,
    val profesor: String?,
    val aula: String?,
    val creditos: Int?,
    val idUsuario: Int
)
