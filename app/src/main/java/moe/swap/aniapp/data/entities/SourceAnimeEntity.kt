package moe.swap.aniapp.data.entities

import androidx.room.*

@Entity(
    tableName = "source_anime",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            childColumns = ["anime_id"],
            parentColumns = ["id"]
        )
    ],
    indices = [
        Index(value = ["anime_id"]),
        Index(value = ["anime_id", "source_id"], unique = true)
    ]
)
data class SourceAnimeEntity(
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "anime_id") val animeId: Int,
    @ColumnInfo(name = "source_id") val sourceId: Long,

    @ColumnInfo(name = "source_anime_identifier") val sourceAnimeIdentifier: String
)