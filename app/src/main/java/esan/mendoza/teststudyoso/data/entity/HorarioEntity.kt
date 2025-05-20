package esan.mendoza.teststudyoso.data.entity

import androidx.room.*
import esan.mendoza.teststudyoso.di.ValidationUtils
import kotlinx.coroutines.flow.Flow
import java.util.Date


@Entity(
    tableName = "horarios",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["id_curso"],
            childColumns = ["id_curso"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Horario(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_horario") val idHorario: Int = 0,
    @ColumnInfo(name = "id_curso") val idCurso: Int,
    @ColumnInfo(name = "dia_semana") val diaSemana: String,
    @ColumnInfo(name = "hora_inicio") val horaInicio: String,
    @ColumnInfo(name = "hora_fin") val horaFin: String
) {
    init {
        require(ValidationUtils.isValidDiaSemana(diaSemana)) { "Día de la semana inválido" }
        require(ValidationUtils.isValidTimeFormat(horaInicio)) { "Formato de hora_inicio inválido" }
        require(ValidationUtils.isValidTimeFormat(horaFin)) { "Formato de hora_fin inválido" }
    }
}