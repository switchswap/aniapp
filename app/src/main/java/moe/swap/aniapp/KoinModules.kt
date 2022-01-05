package moe.swap.aniapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.wax911.library.annotation.processor.GraphProcessor
import io.github.wax911.library.annotation.processor.plugin.AssetManagerDiscoveryPlugin
import io.github.wax911.library.converter.GraphConverter
import io.github.wax911.library.logger.DefaultGraphLogger
import io.github.wax911.library.logger.contract.ILogger
import moe.swap.aniapp.data.AnimeRepository
import moe.swap.aniapp.network.anilist.AnilistProvider
import moe.swap.aniapp.network.anilist.AnilistService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(AnilistService.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(
                GraphConverter(
                    GraphProcessor(
                        AssetManagerDiscoveryPlugin(androidContext().assets),
                        DefaultGraphLogger(ILogger.Level.VERBOSE)
                    ), Gson()
                ).also {
                    it.setMinimumLogLevel(ILogger.Level.VERBOSE)
                }
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AnilistService::class.java)
    }

    single { AnilistProvider(get<AnilistService>()) }
    single { AnimeRepository(get<AnilistProvider>()) }
}