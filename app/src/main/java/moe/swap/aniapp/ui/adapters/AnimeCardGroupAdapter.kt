package moe.swap.aniapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.AdapterAnimeCardGroupBinding
import moe.swap.aniapp.models.AnimeCardGroup
import moe.swap.aniapp.ui.decorations.GridSpacingItemDecoration

class AnimeCardGroupAdapter(private val dataSet: List<AnimeCardGroup>) :
    RecyclerView.Adapter<AnimeCardGroupAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = AdapterAnimeCardGroupBinding.bind(view)

        fun bindData(animeCardGroup: AnimeCardGroup) {
            binding.groupTitle.text = animeCardGroup.title
            if (animeCardGroup.hideGroupTitle) {
                binding.groupTitle.visibility = View.GONE
            }

            // Setup recycler view
            binding.groupAnimeCards.apply {
                layoutManager = GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)
                adapter = AnimeCardAdapter(animeCardGroup.cards)
                addItemDecoration(GridSpacingItemDecoration(1, 25))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_anime_card_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}