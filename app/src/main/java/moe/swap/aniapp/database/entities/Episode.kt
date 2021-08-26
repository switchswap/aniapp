package moe.swap.aniapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "episode_number") val episodeNumber: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "source_episode_identifier") val sourceEpisodeIdentifier: String,

    @Embedded val watchState: EpisodeWatchState?,
    @Embedded val downloadState: EpisodeDownloadState?,
)

data class EpisodeWatchState(
    @ColumnInfo(name = "is_watched") val isDownloaded: Boolean,
    @ColumnInfo(name = "current_timestamp") val currentTimestamp: Int,
    @ColumnInfo(name = "episode_length") val episodeLength: Int,
)

data class EpisodeDownloadState(
    @ColumnInfo(name = "is_downloaded") val isDownloaded: Boolean,
    @ColumnInfo(name = "download_path") val downloadPath: String?,
    @ColumnInfo(name = "bytes_downloaded") val bytesDownloaded: Long,
    @ColumnInfo(name = "file_size") val fileSize: Long,
)


