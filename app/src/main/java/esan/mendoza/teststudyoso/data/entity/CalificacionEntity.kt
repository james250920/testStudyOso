package esan.mendoza.teststudyoso.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "calificaciones",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["id_curso"],
            childColumns = ["id_curso"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TipoPrueba::class,
            parentColumns = ["id_tipo_prueba"],
            childColumns = ["id_tipo_prueba"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Calificacion(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_calificacion") val idCalificacion: Int = 0,
    @ColumnInfo(name = "id_curso") val idCurso: Int,
    @ColumnInfo(name = "id_tipo_prueba") val idTipoPrueba: Int,
    @ColumnInfo(name = "numero_prueba") val numeroPrueba: Int,
    @ColumnInfo(name = "calificacion_obtenida") val calificacionObtenida: Double?
) {
    // Note: Validation for numeroPrueba requires database access, so it's handled in the DAO or ValidationUtils
}
