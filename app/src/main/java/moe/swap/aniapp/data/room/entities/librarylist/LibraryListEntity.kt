package moe.swap.aniapp.data.room.entities.librarylist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

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
