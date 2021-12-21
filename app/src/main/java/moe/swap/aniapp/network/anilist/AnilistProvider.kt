package moe.swap.aniapp.network.anilist

import android.os.Build
import io.github.wax911.library.model.request.QueryContainerBuilder
import moe.swap.aniapp.network.anilist.models.AnimeQueryResult
import moe.swap.aniapp.network.util.GraphContainer
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import retrofit2.Response
import java.time.Year
import java.time.YearMonth
import java.util.*

class AnilistProvider: KoinComponent {

    suspend fun getSeasonPopular(season: String = getSeason(), year: Int = getYear()): Response<GraphContainer<AnimeQueryResult>> {
        val service: AnilistService = get()
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