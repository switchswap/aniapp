package moe.swap.aniapp.data.daos

import androidx.room.*
import moe.swap.aniapp.data.entities.SourceAnimeEntity
import moe.swap.aniapp.data.util.BaseDao

// Insert a new entry for anime data retrieved from a source
// Happens as soon as we ask the source for anime data the first time, and it'll return
// one or more SourceAnime entries. If more than one, the user selects one. The result will be inserted.

// Delete one or more SourceAnime entries. Note that this would also delete any attached episode entries
// and their watch/download progress.

@Dao
interface SourceAnimeDao: BaseDao<SourceAnimeEntity> {

    // Get a single SourceAnime entry for the given anime and source. Whenever a new source (or an initial source)
    // is selected for an anime, we need the SourceAnime entry for it, or query the Source to get it (and also insert it)
    @Query("SELECT * FROM source_anime WHERE anime_id = :animeId AND source_id = :sourceId")
    fun get(animeId: Int, sourceId: Long): SourceAnimeEntity
}