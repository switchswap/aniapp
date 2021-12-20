package moe.swap.aniapp.models

import java.io.Serializable

/**
 * Describes a single anime entry obtained from
 * one of the available providers (i.e. Anilist etc.)
 * Aside from general anime information, this entry also specifies
 * from which provider the data was retrieved, and how it is
 * identified on said provider
 *
 * @param id The unique identifier of this entry, as taken from the
 * currently used anime provider (i.e. Anilist, MAL, etc)
 *
 * @param title The titles of the anime in different formats
 *
 * @param description A short synopsis of the anime title
 *
 * @param episodeCount The total number of episodes of this entry.
 * i.e. if entry describes a single season of the anime,
 * this number refers to the number of episodes of this season.
 * Can be null if the anime has not aired yet or is currently airing
 *
 * @param releaseStatus Describes the state in which the anime is currently in,
 * i.e. is it airing right now, has it already finished airing,
 * or is it yet to be released
 *
 * @param timeUntilNextEpisode When currently releasing, this timestamp describes
 * the day and time at which the upcoming episode will air.
 * If the anime is yet to be released or already finished,
 * this may be null
 *
 * @param season The season in which the anime aired or will air
 *
 * @param year The year in which the anime aired or will air
 *
 * @param rating The decimal rating as given by the provider.
 * This value should be mapped to a 0-10 scale, with decimals
 *
 * @param tags A list of tags for this anime, as given by the provider
 *
 * @param coverUrl A URL to an image showing the cover art of this anime entry
 *
 * @param bannerUrl A URL to an image showing a wide banner art of this anime entry
 *
 * @param selectedSourceId Defines the ID of the source which was selected to be used
 * for this anime. Is set to the default source initially,
 * until the user selects a different one
 * */
data class Anime(
    val id: Int,
    val title: AnimeTitle,
    val description: String,
    val episodeCount: Int?,
    val releaseStatus: AnimeReleaseStatus,
    val timeUntilNextEpisode: Long?,
    val season: AnimeSeason?,
    val year: Int?,
    val rating: Double,
    val tags: List<String>,
    val coverUrl: String,
    val bannerUrl: String,
    val selectedSourceId: Long
): Serializable

/**
 * Stores different representations of an anime title
 * @param native The title of the anime in its native language/script
 * @param english The title of the anime used in english-speaking countries
 * @param romaji The native title of the anime as written in the western writing system
 */
data class AnimeTitle(
    val native: String,
    val english: String,
    val romaji: String
): Serializable

/**
 * Describes the available 4 seasons an anime can air in
 */
enum class AnimeSeason {
    WINTER,
    SPRING,
    SUMMER,
    FALL
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