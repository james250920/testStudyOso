package esan.mendoza.teststudyoso.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "tipos_prueba",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["id_curso"],
            childColumns = ["id_curso"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TipoPrueba(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_tipo_prueba") val idTipoPrueba: Int = 0,
    @ColumnInfo(name = "id_curso") val idCurso: Int,
    @ColumnInfo(name = "nombre_tipo") val nombreTipo: String,
    @ColumnInfo(name = "cantidad_pruebas") val cantidadPruebas: Int,
    @ColumnInfo(name = "peso_total") val pesoTotal: Double
)