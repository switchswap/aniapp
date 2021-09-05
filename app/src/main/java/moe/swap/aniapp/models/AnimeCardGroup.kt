package moe.swap.aniapp.models

data class AnimeCardGroup(
    val title: String,
    val cards: List<Anime>, // This will change soon
    val hideEpisodeNumber: Boolean,
    val hideGroupTitle: Boolean
)
