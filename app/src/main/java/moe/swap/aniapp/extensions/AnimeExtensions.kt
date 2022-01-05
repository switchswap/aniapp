package moe.swap.aniapp.extensions

import android.content.Context
import moe.swap.aniapp.models.Anime
import moe.swap.aniapp.models.AnimeReleaseStatus
import moe.swap.aniapp.models.AnimeSeason
import moe.swap.aniapp.models.AnimeTitle
import moe.swap.aniapp.network.anilist.models.AnimeAnilistEntity
import moe.swap.aniapp.network.anilist.models.AnilistSearchResponse
import moe.swap.aniapp.util.DateUtil

fun Anime.title(context: Context): String {
    return title.english ?: title.romaji ?: title.native ?: "" // Todo: Customize this based on shared prefs
}

fun Anime.nextEpisode(): String {
    return if (timeUntilNextEpisode == null || releaseStatus != AnimeReleaseStatus.RELEASING) {
        "N/A"
    }
    else {
        DateUtil.humanReadableTimeUntil(timeUntilNextEpisode)
    }
}

fun AnilistSearchResponse.entries(): List<AnimeAnilistEntity> {
    return page.media
}

fun AnilistSearchResponse.pageInfo(): AnilistSearchResponse.Page.PageInfo {
    return page.pageInfo
}

fun AnimeAnilistEntity.convertToAnime(): Anime {
    return Anime(
        id,
        AnimeTitle(title.native, title.english, title.romaji),
        description,
        episodes,
        AnimeReleaseStatus.valueOf(status),
        nextAiringEpisode?.airingAt,
        season?.let { AnimeSeason.valueOf(it) },
        seasonYear,
        averageScore?.toDouble(),
        genres,
        coverImage.large,
        bannerImage,
        -1
    )
}