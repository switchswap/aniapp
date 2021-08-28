package moe.swap.aniapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import moe.swap.aniapp.data.room.entities.anime.AnimeDao
import moe.swap.aniapp.data.room.entities.episode.EpisodeDao
import moe.swap.aniapp.data.room.entities.librarylist.LibraryListDao
import moe.swap.aniapp.data.room.entities.sourceAnime.SourceAnimeDao
import moe.swap.aniapp.data.room.entities.AnimeListJunction
import moe.swap.aniapp.data.room.entities.anime.AnimeEntity
import moe.swap.aniapp.data.room.entities.episode.EpisodeEntity
import moe.swap.aniapp.data.room.entities.librarylist.LibraryListEntity
import moe.swap.aniapp.data.room.entities.sourceAnime.SourceAnimeEntity

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