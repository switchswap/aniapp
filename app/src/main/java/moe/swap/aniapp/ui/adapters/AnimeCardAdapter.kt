package moe.swap.aniapp.ui.adapters

import android.app.ActionBar
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.AdapterAnimeCardBinding
import moe.swap.aniapp.models.Anime
import moe.swap.aniapp.ui.activities.AnimeDetailsActivity
import android.util.TypedValue
import kotlin.math.roundToInt


class AnimeCardAdapter(private val dataSet: List<Anime>, private val isHorizontal: Boolean):
    RecyclerView.Adapter<AnimeCardAdapter.ViewHolder>()  {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = AdapterAnimeCardBinding.bind(view)

        fun bindData(anime: Anime, hideEpisodeNumber: Boolean) {
            // Todo: Use glide to set the picture from the cover url
            binding.itemTitle.text = anime.title.english
            "Ep ${anime.episodeCount}".also { binding.itemEpisodeNumber.text = it }
            if (hideEpisodeNumber) {
                binding.itemEpisodeNumber.visibility = View.GONE
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 140 x 257
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_anime_card, parent, false)
        if (isHorizontal) {
            val width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                140f,
                view.resources.displayMetrics
            ).roundToInt()
            view.layoutParams = LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataSet[position], false)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AnimeDetailsActivity::class.java).apply {
                putExtra("anime", dataSet[position])
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}