package moe.swap.aniapp.network.anilist

import android.os.Build
import android.util.Log
import io.github.wax911.library.model.request.QueryContainerBuilder
import moe.swap.aniapp.network.anilist.models.AnilistSearchResponse
import moe.swap.aniapp.network.util.GraphContainer
import retrofit2.Response
import java.time.Year
import java.time.YearMonth
import java.util.*

class AnilistProvider(private val anilistService: AnilistService) {

    suspend fun getSeasonPopular(season: String = getSeason(), year: Int = getYear()): Response<GraphContainer<AnilistSearchResponse>> {
        val queryBuilder = QueryContainerBuilder()
            .putVariables(
                mapOf(
                    "type" to "ANIME",
                    "season" to season,
                    "seasonYear" to year,
                    "sort" to arrayOf("POPULARITY_DESC")
                )
            )
        return anilistService.searchAnime(queryBuilder)
    }

    companion object {
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