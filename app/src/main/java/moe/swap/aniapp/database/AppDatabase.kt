package moe.swap.aniapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

}