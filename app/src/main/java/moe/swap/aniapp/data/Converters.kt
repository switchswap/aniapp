package moe.swap.aniapp.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {

//    /*
//     * Enum Converters
//     */
//    @TypeConverter
//    fun fromAnimeProvider(value: AnimeProvider) = value.name
//
//    @TypeConverter
//    fun toAnimeProvider(value: String) = enumValueOf<AnimeProvider>(value)
//
//    @TypeConverter
//    fun fromAnimeReleaseStatus(value: AnimeReleaseStatus) = value.name
//
//    @TypeConverter
//    fun toAnimeReleaseStatus(value: String) = enumValueOf<AnimeReleaseStatus>(value)
//
//

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}