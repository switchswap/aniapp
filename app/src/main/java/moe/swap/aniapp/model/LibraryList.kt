package moe.swap.aniapp.model

/**
 * A LibraryList describes a predefined
 * or user-defined list to which anime entries can be added.
 * These lists are shown in the user's library
 */
data class LibraryList(
   /**
    * The unique identifier of this entry across the application.
    * It is generated when first added to the database, otherwise null
    */
   val id: Int?,

   /**
    * The name of this list
    */
   val name: String,

   /**
    * A list of anime that are assigned to this list
    */
   val animeOnList: List<Anime>
)
