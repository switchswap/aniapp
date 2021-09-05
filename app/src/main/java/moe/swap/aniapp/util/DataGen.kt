package moe.swap.aniapp.util

import moe.swap.aniapp.models.Anime
import moe.swap.aniapp.models.AnimeReleaseStatus
import moe.swap.aniapp.models.AnimeSeason
import moe.swap.aniapp.models.AnimeTitle
import kotlin.collections.ArrayList

object DataGen {
    fun genAnime(count: Int): List<Anime> {
        val animeList = ArrayList<Anime>()
        for (i in 0 until count) {
            animeList.add(Anime(
                100182,
                AnimeTitle(
                    "ソードアート・オンライン アリシゼーション",
                    "Sword Art Online: Alicization",
                    "Sword Art Online: Alicization",
                ),
                """
                    The third season of Sword Art Online.

                    Kirito awakens in a vast, fantastical forest filled with towering trees. In his search for clues to the truth of his surroundings, he encounters a young boy who seems to know him. He ought to be a simple NPC, but the depth of his emotions seem no different than a human. As they search for the boy's parents, Kirito finds a peculiar memory returning to him. A memory from his own childhood, of this boy and a girl, too, with golden hair, and a name he should have never forgotten - Alice.

                    (Source: Yen Press)

                    Note: The first episode aired with a runtime of ~48 minutes as opposed to the standard 24 minute long episode.
                """,
                24,
                AnimeReleaseStatus.FINISHED,
                null,
                AnimeSeason.FALL,
                2018,
                100f,
                emptyList(),
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx100182-KctPmCJ2smHQ.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/banner/100182-3OWOEFKRzFKI.jpg",
                -1
            ))
        }
        return animeList
    }
}