package esan.mendoza.teststudyoso.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import java.util.Date


@Entity(
    tableName = "Tarea",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["idUsuario"],
            childColumns = ["idUsuario"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["idCurso"],
            childColumns = ["idCurso"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Tarea(
    @PrimaryKey(autoGenerate = true) val idTarea: Int = 0,
    val descripcion: String,
    val esImportante: Boolean?,
    val esUrgente: Boolean?,
    val fechaVencimiento: Date?,
    val fechaCreacion: Date?,
    val estado: String,
    val idUsuario: Int,
    val idCurso: Int?
)