package moe.swap.aniapp.data.entities

import androidx.room.*

@Entity(
    tableName = "episodes",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = SourceAnimeEntity::class,
            childColumns = ["source_anime_id"],
            parentColumns = ["id"]
        )
    ],
    indices = [
        Index(value = ["source_anime_id"]),
        Index(value = ["source_anime_id", "episode_number"], unique = true)
    ]
)
data class EpisodeEntity(
    @ColumnInfo(name = "id") val id: Int,

    // ID of the SourceAnime this episode belongs to
    @ColumnInfo(name = "source_anime_id") val sourceAnimeId: Int,

    // Short string describing the episode numbering, like "10" or "10.5"
    @ColumnInfo(name = "episode_number") val episodeNumber: String,

    // The title of the episode, if given
    @ColumnInfo(name = "title") val title: String?,

    // A description of the episode, if given
    @ColumnInfo(name = "description") val description: String?,

    // A unique identifier of this episode from the source's website. Is used to let the source quickly retrieve episode data
    @ColumnInfo(name = "source_episode_identifier") val sourceEpisodeIdentifier: String,

    // Attached watch and download progress. Initially null, but is added as soon as the user performs either action
    @Embedded val watchState: EpisodeWatchState?,
    @Embedded val downloadState: EpisodeDownloadState?,
)

data class EpisodeWatchState(
    @ColumnInfo(name = "is_watched") val isWatched: Boolean,
    @ColumnInfo(name = "current_timestamp") val currentTimestamp: Int,
    @ColumnInfo(name = "episode_length") val episodeLength: Int,
)

data class EpisodeDownloadState(
    @ColumnInfo(name = "is_downloaded") val isDownloaded: Boolean,
    @ColumnInfo(name = "download_path") val downloadPath: String?,
    @ColumnInfo(name = "bytes_downloaded") val bytesDownloaded: Long,
    @ColumnInfo(name = "file_size") val fileSize: Long,
)


