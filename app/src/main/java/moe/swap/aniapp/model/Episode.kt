package moe.swap.aniapp.model

/**
 * Defines a single episode of a specific anime,
 * gained from a specific source. This implies that there
 * can be multiple Episode instances describing the same episode number
 * of the same anime, but from different sources.
 */
data class Episode(

   /**
    * The unique identifier of this entry across the application.
    * It is generated when first added to the database, otherwise null
    */
   val id: Int?,

   /**
    * An episode is specific to an anime found on a Source,
    * hence this value denotes the ID of that SourceAnime entry
    */
   val sourceAnimeId: Int,

   /**
    * The number of the episode in a format like "10" or "10.5"
    */
   val episodeNumber: String,

   /**
    * If the source provides a title for this episode,
    * the value is stored here. Is null otherwise,
    * and the application will use the episodeNumber instead
    */
   val title: String?,

   /**
    * A short description of the episode, if the source provides one.
    * Is null otherwise
    */
   val description: String?,

   /**
    * A String that uniquely identifies the episode
    * on the source's website. Can be any format specific
    * to the source it was taken from, like a numeric ID,
    * a hash or a website url
    */
   val sourceEpisodeIdentifier: String,

   /**
    * A reference to the user's watching progress of the episode.
    * Is initially set to null, until the user has started watching
    * this episode
    */
   val watchState: EpisodeWatchState?,

   /**
    * A reference to the user's download progress of the episode.
    * Is initially set to null, until the user has started downloading
    * this episode
    */
   val downloadState: EpisodeDownloadState?,
)

/**
 * Describes the user's progress in watching an episode
 */
data class EpisodeWatchState(

   /**
    * Defines whether or not the episode
    * has been marked as watched.
    * This can either happen automatically when
    * most of the episode has been watched,
    * or manually if the user marks the episode as watched
    */
   val isWatched: Boolean,

   /**
    * If the episode is not marked as watched,
    * this value may store the time in seconds
    * up to which point the user has watched the episode.
    * Is used for continuing playback where the user has left off
    * and set by the player after the user stops playback
    */
   val currentTimestamp: Int,

   /**
    * Stores the length of the episode's video in seconds.
    * Just like the currentTimestamp, this is set by the player
    * when the user stops watching, as the player is the only
    * entity aware of the video's length
    */
   val episodeLength: Int,
)

/**
 * Describes the user's progress of downloading an episode
 */
data class EpisodeDownloadState(

   /**
    * Defines whether or not the episode
    * has been **completely** downloaded.
    * If the episode has not or only partially
    * been downloaded, this is still set to false.
    * Likewise, if set to true, it is required that
    * a downloadPath is set, pointing to the file where
    * the episode has been downloaded to.
    */
   val isDownloaded: Boolean,

   /**
    * The file path to the downloaded video file.
    */
   val downloadPath: String,

   /**
    * Describes the number of bytes that have been already downloaded.
    * Is used for tracking download progress or for continuing paused downloads
    */
   val bytesDownloaded: Long,

   /**
    * Stores the size of the episode's video file in bytes.
    * Also used for tracking download progress
    */
   val fileSize: Long,
)


