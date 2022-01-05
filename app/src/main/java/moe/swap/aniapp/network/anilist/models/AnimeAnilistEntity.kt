package moe.swap.aniapp.network.anilist.models

data class AnimeAnilistEntity(
    val averageScore: Int?,
    val bannerImage: String?,
    val coverImage: CoverImage,
    val description: String,
    val duration: Int,
    val endDate: EndDate,
    val episodes: Int?,
    val format: String,
    val genres: List<String>,
    val id: Int,
    val isAdult: Boolean,
    val mediaListEntry: Any?, // TODO: Fix this
    val nextAiringEpisode: NextAiringEpisode?,
    val popularity: Int,
    val season: String?,
    val seasonYear: Int?,
    val startDate: StartDate,
    val status: String,
    val studios: Studios,
    val title: Title,
    val type: String
) {
    data class CoverImage(
        val large: String
    )

    data class EndDate(
        val day: Int,
        val month: Int,
        val year: Int
    )

    data class StartDate(
        val day: Int,
        val month: Int,
        val year: Int
    )

    data class Studios(
        val edges: List<Edge>
    ) {
        data class Edge(
            val isMain: Boolean,
            val node: Node
        ) {
            data class Node(
                val id: Int,
                val name: String
            )
        }
    }

    data class Title(
        val english: String,
        val native: String,
        val romaji: String,
        val userPreferred: String
    )

    data class NextAiringEpisode (
        val airingAt: Long,
        val timeUntilAiring: Long,
        val episode: Int

    )
}
