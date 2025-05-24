package esan.mendoza.teststudyoso.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Index


@Entity(
    tableName = "usuario",  // Mantener así ya que las queries usan este nombre
    indices = [Index(value = ["correo"], unique = true)]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true) val idUsuario: Int = 0,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val correo: String,
    val contrasena: String
)