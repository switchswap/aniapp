package moe.swap.aniapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "lists",
    primaryKeys = ["id"],
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class LibraryListEntity(
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "name") val name: String
)
