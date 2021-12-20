package moe.swap.aniapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import moe.swap.aniapp.network.anilist.AnilistRepository
import moe.swap.aniapp.network.util.getError

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("moe.swap.aniapp", appContext.packageName)
    }

    @Test
    fun testGetTrendingAnime() {
        val anilistRepository = AnilistRepository()
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        runBlocking {
            val request = anilistRepository.getTrendingAnime(appContext, "FALL", 2021)
            assertEquals(200, request.code())
        }
    }
}