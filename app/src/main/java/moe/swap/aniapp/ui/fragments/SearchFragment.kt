package moe.swap.aniapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.FragmentSearchBinding
import moe.swap.aniapp.ui.adapters.AnimeCardPagedAdapter
import moe.swap.aniapp.ui.viewmodels.SearchViewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels<SearchViewModel>()
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var searchJob: Job? = null
    private lateinit var adapter: AnimeCardPagedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AnimeCardPagedAdapter()

        binding.apply {
            searchResults.setHasFixedSize(true)
            searchResults.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            searchResults.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)

        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search for anime!"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus();
                if (!query.isNullOrBlank()) {
                    search(query)
                }
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun search(query: String) {
        // Cancel any previous job before making a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            searchViewModel.searchAnime(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}