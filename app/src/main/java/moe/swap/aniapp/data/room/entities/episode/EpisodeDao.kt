package moe.swap.aniapp.data.room.entities.episode

import androidx.room.*
import moe.swap.aniapp.data.room.util.BaseDao

// upsert episode(s) whenever there's a new batch of episodes coming in for a source

@Dao
interface EpisodeDao: BaseDao<EpisodeEntity> {

    @Query("SELECT * FROM episodes WHERE source_anime_id = :sourceAnimeId AND episode_number = :epNumber")
    fun get(sourceAnimeId: Int, epNumber: String): EpisodeEntity

    @Query("SELECT * FROM episodes WHERE source_anime_id = :sourceAnimeId")
    fun getAllForSourceAnime(sourceAnimeId: Int): List<EpisodeEntity>

    @Query("""
        SELECT episodes.*
        FROM episodes 
        INNER JOIN source_anime
        ON episodes.source_anime_id = source_anime.id
        WHERE source_anime.source_id = :sourceId
    """)
    fun getAllFromSource(sourceId: Long): List<EpisodeEntity>
}