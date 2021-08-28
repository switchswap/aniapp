package moe.swap.aniapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import moe.swap.aniapp.data.daos.AnimeDao
import moe.swap.aniapp.data.daos.EpisodeDao
import moe.swap.aniapp.data.daos.LibraryListDao
import moe.swap.aniapp.data.daos.SourceAnimeDao
import moe.swap.aniapp.data.entities.*
import moe.swap.aniapp.data.entities.relations.AnimeListJunction

@Database(
    entities = [
        AnimeEntity::class,
        SourceAnimeEntity::class,
        EpisodeEntity::class,
        LibraryListEntity::class,
        AnimeListJunction::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun sourceAnimeDao(): SourceAnimeDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun libraryListDao(): LibraryListDao
}