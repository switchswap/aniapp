package moe.swap.aniapp.ui.activities

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.ActivityAnimeDetailsBinding
import moe.swap.aniapp.extensions.nextEpisode
import moe.swap.aniapp.extensions.title
import moe.swap.aniapp.models.Anime
import moe.swap.aniapp.util.DateUtil
import kotlin.math.roundToInt

class AnimeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeDetailsBinding
    private lateinit var anime: Anime
    private var descriptionExpanded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        anime = intent.extras?.get("anime") as Anime

        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            collapsingToolbar.title = anime.title(baseContext)

            animeDetail.animeDetailInfo?.apply {
                animeTitle.text = anime.title(baseContext)
                animeEpisodeCount.text =
                    if (anime.episodeCount != null) anime.episodeCount.toString() else "N/A"
                animeRating.text = if (anime.rating != null) getString(
                    R.string.percentage,
                    anime.rating!!.roundToInt()
                ) else "N/A"
                animeNextEpisode.text = anime.nextEpisode()

                val description = anime.description.trimMargin()
                animeDescription.text = description

                animeDescription.post {
                    // If not ellipsized, hide more button
                    if (animeDescription.layout?.text.toString() == description) {
                        buttonMore.visibility = View.GONE
                    }
                    else {
                        buttonMore.visibility = View.VISIBLE

                        // Set on click to toggle text view size
                        buttonMore.setOnClickListener {
                            if (descriptionExpanded) {
                                animeDescription.maxLines = 3
                                buttonMore.text = "More"
                                descriptionExpanded = false
                            }
                            else {
                                animeDescription.maxLines = Int.MAX_VALUE
                                buttonMore.text = "Less"
                                descriptionExpanded = true
                            }
                        }
                    }
                }
            }

            // Load in banner and cover via glide
            Glide.with(baseContext)
                .load(anime.bannerUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(animeBanner)

            animeDetail.animeDetailInfo?.animeCover?.let {
                Glide.with(baseContext)
                    .load(anime.coverUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(com.google.android.material.R.drawable.mtrl_ic_error)
                    .into(it)
            }
        }
    }
}