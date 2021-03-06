package moe.swap.aniapp.network.anilist

import io.github.wax911.library.annotation.GraphQuery
import io.github.wax911.library.model.request.QueryContainerBuilder
import moe.swap.aniapp.network.anilist.models.AnilistSearchResponse
import moe.swap.aniapp.network.util.GraphContainer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AnilistService {
    @POST(".")
    @GraphQuery("AnimeSearch")
    suspend fun searchAnime(
        @Body builder: QueryContainerBuilder
    ): Response<GraphContainer<AnilistSearchResponse>>


    companion object {
        const val BASE_URL = "https://graphql.anilist.co/"
    }
}