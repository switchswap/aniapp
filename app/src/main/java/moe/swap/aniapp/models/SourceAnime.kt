package moe.swap.aniapp.models

/**
 * This class defines a single entry of an anime
 * retrieved from a specific source.
 * It specifies both the ID of the Anime entry it describes,
 * as well as the ID of the source it was taken from
 *
 * @param id The unique identifier of this entry across the application.
 * It is generated when first added to the database, otherwise null
 *
 * @param animeId The ID of the Anime entry this SourceAnime describes
 *
 * @param sourceId The ID of the source where this entry was found
 *
 * @param sourceIdentifier A String that uniquely identifies the anime
 * on the source's website. Can be any format specific
 * to the source it was taken from, like a numeric ID,
 * a hash or a website url
 */
data class SourceAnime(
    val id: Int?,
    val animeId: Int,
    val sourceId: Long,
    val sourceIdentifier: String
)
