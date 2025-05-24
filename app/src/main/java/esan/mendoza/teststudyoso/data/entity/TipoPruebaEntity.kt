package esan.mendoza.teststudyoso.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "TipoPrueba",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["idCurso"],
            childColumns = ["idCurso"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TipoPrueba(
    @PrimaryKey(autoGenerate = true) val idTipoPrueba: Int = 0,
    val idCurso: String,
    val nombreTipo: String,
    val cantidadPruebas: Int,
    val pesoTotal: Double
)