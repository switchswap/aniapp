package moe.swap.aniapp.data.room.entities.anime

import androidx.room.*
import moe.swap.aniapp.data.room.util.BaseDao

// insert anime as soon as possible, likely when clicking on a search result.
// all other actions (querying sources, episodes, adding anime to lists) depend on the anime being inserted

// update anime when needed. Especially when migrating to another provider,
// and when selecting another source for the anime, but perhaps also when pull-to-refreshing the detail page

@Dao
interface AnimeDao: BaseDao<AnimeEntity> {

    // get a single anime by ID. Used basically everywhere
    @Query("SELECT * FROM anime WHERE id = :animeId")
    fun get(animeId: Int): AnimeEntity

    // get all anime from the table when needed .. Could be important for provider migration and potentially for backups
    @Query("SELECT * FROM anime")
    fun getAll(): List<AnimeEntity>

    // get all anime that were added to the given list. Not sure if needed yet, as querying all lists with all their anime could be used instead.
    @Query("""
        SELECT anime.*
        FROM anime 
        INNER JOIN anime_list_junction AS junction 
        ON anime.id = junction.anime_id 
        WHERE junction.list_id = :listId
    """)
    fun getAnimeOnList(listId: Int): List<AnimeEntity>
}