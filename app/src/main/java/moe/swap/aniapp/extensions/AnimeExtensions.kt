package moe.swap.aniapp.extensions

import android.content.Context
import moe.swap.aniapp.models.Anime

fun Anime.title(context: Context): String {
    return title.english // Todo: Customize this based on shared prefs
}