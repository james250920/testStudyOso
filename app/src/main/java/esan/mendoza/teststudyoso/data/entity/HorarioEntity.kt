package esan.mendoza.teststudyoso.data.entity

import androidx.room.*



@Entity(
    tableName = "Horario",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["idCurso"],
            childColumns = ["idCurso"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Horario(
    @PrimaryKey(autoGenerate = true) val idHorario: Int = 0,
    val idCurso: Int,
    val diaSemana: String,
    val horaInicio: String,
    val horaFin: String
)