package moe.swap.aniapp.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.AdapterAnimeCardBinding
import moe.swap.aniapp.extensions.title
import moe.swap.aniapp.models.Anime
import moe.swap.aniapp.models.AnimeReleaseStatus
import moe.swap.aniapp.ui.activities.AnimeDetailsActivity

class AnimeCardPagedAdapter :
    PagingDataAdapter<Anime, AnimeCardPagedAdapter.AnimeCardViewHolder>(ANIME_COMPARATOR) {

    class AnimeCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = AdapterAnimeCardBinding.bind(view)

        fun bindData(anime: Anime, hideEpisodeNumber: Boolean) {
            binding.apply {
                itemTitle.text = anime.title(itemView.context)
                "Ep ${anime.episodeCount}".also { binding.itemEpisodeNumber.text = it }
                if (hideEpisodeNumber) {
                    itemEpisodeNumber.visibility = View.GONE
                }

                // Load in image via glide
                Glide.with(itemView)
                    .load(anime.coverUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(com.google.android.material.R.drawable.mtrl_ic_error)
                    .into(itemCover)
            }
        }
    }

    override fun onBindViewHolder(holder: AnimeCardViewHolder, position: Int) {
        val anime: Anime? = getItem(position)
        if (anime != null) {
            holder.bindData(anime, anime.releaseStatus != AnimeReleaseStatus.RELEASING)
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, AnimeDetailsActivity::class.java).apply {
                    putExtra("anime", anime)
                }
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_anime_card, parent, false)
        return AnimeCardViewHolder(view)
    }

    companion object {
        private val ANIME_COMPARATOR = object: DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem == newItem
            }
        }
    }
}