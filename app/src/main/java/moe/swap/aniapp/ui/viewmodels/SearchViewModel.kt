package moe.swap.aniapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import moe.swap.aniapp.data.AnimeRepository
import moe.swap.aniapp.models.Anime
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SearchViewModel : ViewModel(), KoinComponent {
    private val animeRepository: AnimeRepository = get()
    private var currentQuery: String? = null
    private var currentSearchResult: Flow<PagingData<Anime>>? = null

    fun searchAnime(query: String): Flow<PagingData<Anime>> {
        currentQuery = query
        val newResult: Flow<PagingData<Anime>> = animeRepository.getSearchResults(query)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}