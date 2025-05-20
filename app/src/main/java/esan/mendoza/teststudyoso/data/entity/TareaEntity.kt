package esan.mendoza.teststudyoso.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import esan.mendoza.teststudyoso.di.ValidationUtils
import java.util.Date


@Entity(
    tableName = "tareas",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["id_curso"],
            childColumns = ["id_curso"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Tarea(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_tarea") val idTarea: Int = 0,
    @ColumnInfo(name = "descripcion") val descripcion: String,
    @ColumnInfo(name = "es_importante") val esImportante: Boolean?,
    @ColumnInfo(name = "es_urgente") val esUrgente: Boolean?,
    @ColumnInfo(name = "fecha_vencimiento") val fechaVencimiento: Date?,
    @ColumnInfo(name = "fecha_creacion") val fechaCreacion: Date?,
    @ColumnInfo(name = "estado", defaultValue = "Pendiente") val estado: String,
    @ColumnInfo(name = "id_usuario") val idUsuario: Int,
    @ColumnInfo(name = "id_curso") val idCurso: Int?
) {
    init {
        require(ValidationUtils.isValidEstado(estado)) { "Estado inválido" }
    }
}