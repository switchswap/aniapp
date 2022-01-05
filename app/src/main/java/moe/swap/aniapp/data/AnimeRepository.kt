package moe.swap.aniapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import moe.swap.aniapp.extensions.convertToAnime
import moe.swap.aniapp.extensions.entries
import moe.swap.aniapp.models.Anime
import moe.swap.aniapp.network.anilist.AnilistProvider
import moe.swap.aniapp.network.util.GraphQLResponse

class AnimeRepository(private val anilistProvider: AnilistProvider) {
    suspend fun getSeasonPopular(): List<Anime> {
        val response = GraphQLResponse(anilistProvider.getSeasonPopular())
        val entries = response.getBodyOrThrow().entries()
        return entries.map { it.convertToAnime() }
    }

    fun getSearchResults(query: String): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnilistPagingSource(anilistProvider, query) }
        ).flow
    }
}

