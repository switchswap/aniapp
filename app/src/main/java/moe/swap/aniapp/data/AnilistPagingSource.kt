package moe.swap.aniapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import moe.swap.aniapp.extensions.convertToAnime
import moe.swap.aniapp.extensions.entries
import moe.swap.aniapp.extensions.pageInfo
import moe.swap.aniapp.models.Anime
import moe.swap.aniapp.network.anilist.AnilistProvider
import moe.swap.aniapp.network.anilist.AnilistService
import moe.swap.aniapp.network.util.GraphQLResponse
import java.io.IOException
import java.lang.Exception

private const val ANILIST_STARTING_PAGE_INDEX = 1

class AnilistPagingSource(
    private val anilistProvider:  AnilistProvider,
    private val query: String
    ) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val position = params.key ?: ANILIST_STARTING_PAGE_INDEX

        return try {
            val response = GraphQLResponse(anilistProvider.getSearchResult(query, position))
            val responseBody = response.getBodyOrThrow()
            val pageInfo = responseBody.pageInfo()
            val results: List<Anime> = responseBody.entries().map { it.convertToAnime() }

            LoadResult.Page(
                data = results,
                prevKey = if (position == ANILIST_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (!pageInfo.hasNextPage) null else position + 1
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
            Log.e("PAGING_SOURCE", e.message + "")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}