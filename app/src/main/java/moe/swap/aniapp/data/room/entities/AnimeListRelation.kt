package moe.swap.aniapp.data.room.entities

import androidx.room.*
import moe.swap.aniapp.data.room.entities.anime.AnimeEntity
import moe.swap.aniapp.data.room.entities.librarylist.LibraryListEntity

@Entity(
    tableName = "anime_list_junction",
    primaryKeys = ["anime_id", "list_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            childColumns = ["anime_id"],
            parentColumns = ["id"],
        ),
        ForeignKey(
            entity = LibraryListEntity::class,
            childColumns = ["list_id"],
            parentColumns = ["id"],
        )
    ],
    indices = [
        Index(value = ["anime_id"]),
        Index(value = ["list_id"]),
        Index(value = ["anime_id", "list_id"], unique = true)
    ]
)
data class AnimeListJunction(
    @ColumnInfo(name = "anime_id") val animeId: Int,
    @ColumnInfo(name = "list_id") val listId: Int
)

data class ListWithAnime(
    @Embedded val list: LibraryListEntity,

    @Relation(
        parentColumn = "id",
        entity = AnimeEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = AnimeListJunction::class,
            parentColumn = "list_id",
            entityColumn = "anime_id"
        )
    )
    val animeOnList: List<AnimeEntity>
)

