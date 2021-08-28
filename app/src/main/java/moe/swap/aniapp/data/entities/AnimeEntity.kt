package moe.swap.aniapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "anime",
    primaryKeys = ["id"],
)
data class AnimeEntity(
    @ColumnInfo(name= "id") val id: Int,

    @ColumnInfo(name = "provider") val provider: AnimeProvider,
    @ColumnInfo(name = "provider_anime_id") val providerAnimeId: Int,

    @ColumnInfo(name = "title_native") val titleNative: String,
    @ColumnInfo(name = "title_english") val titleEnglish: String,
    @ColumnInfo(name = "title_romaji") val titleRomaji: String,

    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "episode_count") val episodeCount: Int,
    @ColumnInfo(name = "release_status") val releaseStatus: AnimeReleaseStatus,
    @ColumnInfo(name = "next_episode_date") val nextEpisodeDate: Date?,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "tags") val tags: String,

    @ColumnInfo(name = "cover_url") val coverUrl: String,
    @ColumnInfo(name = "banner_url") val bannerUrl: String,
)

enum class AnimeProvider {
    ANILIST,
    MY_ANIME_LIST,
    KITSU
}

enum class AnimeReleaseStatus {
    FINISHED,
    RELEASING,
    NOT_YET_RELEASED,
    CANCELLED,
    HIATUS
}