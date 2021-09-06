package moe.swap.aniapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.AdapterAnimeCardBinding
import moe.swap.aniapp.models.Anime

class AnimeCardAdapter(private val dataSet: List<Anime>):
    RecyclerView.Adapter<AnimeCardAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = AdapterAnimeCardBinding.bind(view)

        init {
            itemView.setOnClickListener(this)
        }

        fun bindData(anime: Anime, hideEpisodeNumber: Boolean) {
            // Todo: Use glide to set the picture from the cover url
            binding.itemTitle.text = anime.title.english
            "Ep ${anime.episodeCount}".also { binding.itemEpisodeNumber.text = it }
            if (hideEpisodeNumber) {
                binding.itemEpisodeNumber.visibility = View.GONE
            }
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_anime_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataSet[position], false)
    }

    override fun getItemCount(): Int = dataSet.size
}