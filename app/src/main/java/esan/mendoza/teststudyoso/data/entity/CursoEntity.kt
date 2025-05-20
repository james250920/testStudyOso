package esan.mendoza.teststudyoso.data.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Entity(
    tableName = "cursos",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Curso(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_curso") val idCurso: Int = 0,
    @ColumnInfo(name = "nombre_curso") val nombreCurso: String,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "profesor") val profesor: String?,
    @ColumnInfo(name = "aula") val aula: String?,
    @ColumnInfo(name = "creditos") val creditos: Int?,
    @ColumnInfo(name = "id_usuario") val idUsuario: Int
)
