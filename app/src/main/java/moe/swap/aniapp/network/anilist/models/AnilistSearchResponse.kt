package moe.swap.aniapp.network.anilist.models

import com.google.gson.annotations.SerializedName

data class AnilistSearchResponse(
    val `data`: Data
) {
    data class Data(
        // TODO: serialized name Page
        @SerializedName("Page")
        val page: Page
    ) {
        data class Page(
            val media: List<AnimeAnilistEntity>,
            val pageInfo: PageInfo
        ) {
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