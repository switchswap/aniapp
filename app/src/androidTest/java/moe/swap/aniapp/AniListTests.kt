package moe.swap.aniapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.wax911.library.annotation.processor.GraphProcessor
import io.github.wax911.library.annotation.processor.plugin.AssetManagerDiscoveryPlugin
import io.github.wax911.library.converter.GraphConverter
import io.github.wax911.library.logger.DefaultGraphLogger
import io.github.wax911.library.logger.contract.ILogger
import kotlinx.coroutines.runBlocking
import moe.swap.aniapp.data.AnimeRepository
import moe.swap.aniapp.network.anilist.AnilistProvider
import moe.swap.aniapp.network.anilist.AnilistService
import moe.swap.aniapp.network.util.GraphQLResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AniListTests {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("moe.swap.aniapp", appContext.packageName)
    }

    @Test
    fun get_season_popular() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        val anilistService = Retrofit.Builder()
                .baseUrl(AnilistService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    GraphConverter(
                        GraphProcessor(
                            AssetManagerDiscoveryPlugin(InstrumentationRegistry.getInstrumentation().targetContext.assets),
                            DefaultGraphLogger(ILogger.Level.VERBOSE)
                        ), Gson()
                    ).also {
                        it.setMinimumLogLevel(ILogger.Level.VERBOSE)
                    }
                )
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(AnilistService::class.java)

        val anilistProvider = AnilistProvider(anilistService)
        runBlocking {
            val response = GraphQLResponse(anilistProvider.getSeasonPopular("FALL", 2021))
            val responseBody = response.getBody()
            // We should get a response body
            assertNotNull(responseBody)

            val mediaItems = responseBody!!.page.media
            assertEquals(20, mediaItems.size)
        }
    }
}