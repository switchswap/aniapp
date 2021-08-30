package moe.swap.aniapp.model

/**
 * A LibraryList describes a predefined
 * or user-defined list to which anime entries can be added.
 * These lists are shown in the user's library
 *
 * @param id The unique identifier of this entry across the application.
 * It is generated when first added to the database, otherwise null
 *
 * @param name The name of this list
 *
 * @param animeOnList A list of anime that are assigned to this list
 */
data class LibraryList(
    val id: Int?,
    val name: String,
    val animeOnList: List<Anime>
)
