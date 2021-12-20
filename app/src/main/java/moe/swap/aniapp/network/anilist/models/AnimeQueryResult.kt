package moe.swap.aniapp.network.anilist.models

import com.google.gson.annotations.SerializedName

data class AnimeQueryResult(
    val `data`: Data
) {
    data class Data(
        // TODO: serialized name Page
        @SerializedName("Page")
        val page: Page
    ) {
        data class Page(
            val media: List<Media>,
            val pageInfo: PageInfo
        ) {
            data class Media(
                val averageScore: Int,
                val bannerImage: String,
                val coverImage: CoverImage,
                val description: String,
                val duration: Int,
                val endDate: EndDate,
                val episodes: Int,
                val format: String,
                val genres: List<String>,
                val id: Int,
                val isAdult: Boolean,
                val mediaListEntry: Any,
                val nextAiringEpisode: Any,
                val popularity: Int,
                val season: String,
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
            }

            data class PageInfo(
                val currentPage: Int,
                val hasNextPage: Boolean,
                val lastPage: Int,
                val perPage: Int,
                val total: Int
            )
        }
    }
}