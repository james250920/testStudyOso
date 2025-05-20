package esan.mendoza.teststudyoso.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Index


@Entity(
    tableName = "usuarios",
    indices = [Index(value = ["correo"], unique = true)]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_usuario") val idUsuario: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellido") val apellido: String,
    @ColumnInfo(name = "fecha_nacimiento") val fechaNacimiento: String,
    @ColumnInfo(name = "correo") val correo: String,
    @ColumnInfo(name = "contrasena") val contrasena: String // Hash de la contraseña
)