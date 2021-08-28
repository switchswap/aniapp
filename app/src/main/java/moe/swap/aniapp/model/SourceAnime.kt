package moe.swap.aniapp.model

/**
 * This class defines a single entry of an anime
 * retrieved from a specific source.
 * It specifies both the ID of the Anime entry it describes,
 * as well as the ID of the source it was taken from
 */
data class SourceAnime(

   /**
    * The unique identifier of this entry across the application.
    * It is generated when first added to the database, otherwise null
    */
   val id: Int?,

   /**
    * The ID of the Anime entity this AnimeOnSource describes.
    */
   val animeId: Int,

   /**
    * The ID of the source where this entry was found
    */
   val sourceId: Long,

   /**
    * A String that uniquely identifies the anime
    * on the source's website. Can be any format specific
    * to the source it was taken from, like a numeric ID,
    * a hash or a website url
    */
   val sourceAnimeIdentifier: String
)
