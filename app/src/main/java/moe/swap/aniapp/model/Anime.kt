package moe.swap.aniapp.model

import java.util.Date

/**
 * Describes a single anime entry obtained from
 * one of the available providers (i.e. Anilist etc.)
 * Aside from general anime information, this entry also specifies
 * from which provider the data was retrieved, and how it is
 * identified on said provider.
 */
data class Anime(

    /**
     * The unique identifier of this entry across the application.
     * It is generated when first added to the database, otherwise null
     */
    val id: Int?,

    /**
     * The provider from which this entry was taken
     */
    val provider: AnimeProvider,

    /**
     * The ID of the anime as defined by the provider
     */
    val providerAnimeId: Int,

    /**
     * The title of the anime written in its native language
     */
    val titleNative: String,

    /**
     * The English title of the anime
     */
    val titleEnglish: String,

    /**
     * The title of the anime as written in the western writing system
     */
    val titleRomaji: String,

    /**
     * A short synopsis of the anime title
     */
    val description: String,

    /**
     * The total number of episodes of this entry.
     * i.e. if entry describes a single season of the anime,
     * this number refers to the number of episodes of this season.
     * Can be null if the anime has not aired yet or is currently airing
     */
    val episodeCount: Int?,

    /**
     * Describes the state in which the anime is currently in,
     * i.e. is it airing right now, has it already finished airing,
     * or is it yet to be released
     */
    val releaseStatus: AnimeReleaseStatus,

    /**
     * When currently releasing, this date describes
     * the day and time at which the upcoming episode will air.
     * If the anime is yet to be released or already finished,
     * this may be null
     */
    val nextEpisodeDate: Date?,

    /**
     * The decimal rating as given by the provider.
     * This value should be mapped to a 0-10 scale, with decimals
     */
    val rating: Float,

    /**
     * A list of tags for this anime, as given by the provider
     */
    val tags: List<String>,

    /**
     * A URL to an image showing the cover art of this anime entry
     */
    val coverUrl: String,

    /**
     * A URL to an image showing a wide banner art of this anime entry
     */
    val bannerUrl: String,

    /**
     * Defines the ID of the source which was selected to be used
     * for this anime. Is set to the default source initially,
     * until the user selects a different one
     */
    val selectedSourceId: Long
)

/**
 * Describes the provider from which an anime entry
 * has been retrieved
 */
enum class AnimeProvider {
    ANILIST,
    MY_ANIME_LIST,
    KITSU
}

/**
 * Describes the releasing status of the anime,
 * whether it's already finished airing, about to air,
 * or yet to be aired
 */
enum class AnimeReleaseStatus {
    FINISHED,
    RELEASING,
    NOT_YET_RELEASED,
    CANCELLED,
    HIATUS
}