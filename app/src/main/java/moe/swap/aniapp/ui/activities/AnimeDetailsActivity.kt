package moe.swap.aniapp.ui.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.ActivityAnimeDetailsBinding
import moe.swap.aniapp.extensions.title
import moe.swap.aniapp.models.Anime
import kotlin.math.roundToInt

class AnimeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeDetailsBinding
    private lateinit var anime: Anime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        anime = intent.extras?.get("anime") as Anime

        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.animeBanner.setImageURI(anime.bannerUrl)
        binding.collapsingToolbar.title = anime.title(this)

        binding.animeDetail.animeDetailInfo?.apply {
            animeTitle.text = anime.title(baseContext)
            animeEpisodeCount.text = anime.episodeCount.toString()
            animeRating.text = getString(R.string.percentage, anime.rating.roundToInt())
//            animeNextEpisode.text = anime.timeUntilNextEpisode.toString() // TODO: Make this a string
            animeDescription.text = anime.description.trim()
        }
    }
}