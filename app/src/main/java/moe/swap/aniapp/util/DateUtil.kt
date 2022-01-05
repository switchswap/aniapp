package moe.swap.aniapp.util

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

object DateUtil {
    fun humanReadableTimeUntil(targetTime: Long): String {
        val difference = abs((Date().time / 1000) - targetTime)
        val days = TimeUnit.SECONDS.toDays(difference)
        val hours = TimeUnit.SECONDS.toHours(difference) - (days * 24)
        return "${days}d ${hours}h"
    }
}