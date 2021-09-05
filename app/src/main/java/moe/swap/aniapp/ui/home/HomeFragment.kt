package moe.swap.aniapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import moe.swap.aniapp.databinding.FragmentHomeBinding
import moe.swap.aniapp.models.AnimeCardGroup
import moe.swap.aniapp.ui.adapters.AnimeCardGroupAdapter
import moe.swap.aniapp.util.DataGen.genAnime

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animeCards = genAnime(5)

        val continueWatchingGroup = AnimeCardGroup("Continue Watching", animeCards, false, false)
        val seasonPopularGroup = AnimeCardGroup("Popular This Season", animeCards, false, false)

        // Setup recycler view
        binding.homeCardGroups.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AnimeCardGroupAdapter(listOf(continueWatchingGroup, seasonPopularGroup))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}