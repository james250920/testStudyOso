package esan.mendoza.teststudyoso.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Calificacion",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["idCurso"],
            childColumns = ["idCurso"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TipoPrueba::class,
            parentColumns = ["idTipoPrueba"],
            childColumns = ["idTipoPrueba"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Calificacion(
    @PrimaryKey(autoGenerate = true) val idCalificacion: Int = 0,
    val idCurso: Int,
    val idTipoPrueba: Int,
    val numeroPrueba: Int,
    val calificacionObtenida: Double?
)
