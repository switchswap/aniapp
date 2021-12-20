package moe.swap.aniapp.network.anilist

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.net.toUri
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.wax911.library.annotation.processor.GraphProcessor
import io.github.wax911.library.annotation.processor.plugin.AssetManagerDiscoveryPlugin
import io.github.wax911.library.converter.GraphConverter
import io.github.wax911.library.logger.DefaultGraphLogger
import io.github.wax911.library.logger.contract.ILogger
import io.github.wax911.library.model.request.QueryContainerBuilder
import moe.swap.aniapp.network.anilist.models.AnimeQueryResult
import moe.swap.aniapp.network.util.GraphContainer
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Year
import java.time.YearMonth
import java.util.*

class AnilistRepository {

    suspend fun getTrendingAnime(context: Context, season: String = getSeason(), year: Int = getYear()): Response<GraphContainer<AnimeQueryResult>> {
        val service = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(
                GraphConverter(
                    GraphProcessor(
                        AssetManagerDiscoveryPlugin(assetManager = context.assets),
                        logger = DefaultGraphLogger(ILogger.Level.VERBOSE)
                    ), Gson()
                ).also {
                    it.setMinimumLogLevel(ILogger.Level.VERBOSE)
                }
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AnilistService::class.java)

        val queryBuilder = QueryContainerBuilder()
            .putVariables(
                mapOf(
                    "type" to "ANIME",
                    "season" to season,
                    "seasonYear" to year,
                    "sort" to arrayOf("POPULARITY_DESC")
                )
            )
        return service.searchAnime(queryBuilder)
    }


    companion object {
        private const val clientId = "385"
        private const val apiUrl = "https://graphql.anilist.co/"
        private const val baseUrl = "https://anilist.co/api/v2/"
        private const val baseAnimeUrl = "https://anilist.co/anime/"

        fun animeUrl(mediaId: Int): String {
            return baseAnimeUrl + mediaId
        }

        fun authUrl(): Uri = "${baseUrl}oauth/authorize".toUri().buildUpon()
            .appendQueryParameter("client_id", clientId)
            .appendQueryParameter("response_type", "token")
            .build()

        /**
         * Return current year for use in Anilist GraphQL query
         */
        fun getYear(): Int {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                Calendar.getInstance().get(Calendar.YEAR)
            } else {
                Year.now().value
            }
        }

        /**
         * Return current season for use in Anilist GraphQL query
         * @return Any one of ["WINTER", "SPRING", "SUMMER", "FALL"]
         */
        fun getSeason(): String {
            val month: Int = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                // Jan is 0 with `Calendar` class
                Calendar.getInstance().get(Calendar.MONTH) + 1
            }
            else {
                YearMonth.now().month.value
            }

            return when (month) {
                3,4,5 -> "SPRING"
                6,7,8 -> "SUMMER"
                9,10,11 -> "FALL"
                12,1,2 -> "WINTER"
                else -> ""
            }
        }
    }
}