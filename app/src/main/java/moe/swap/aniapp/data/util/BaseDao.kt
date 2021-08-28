package moe.swap.aniapp.data.util

import androidx.room.*

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(items: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(item: T)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(items: List<T>)

    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun delete(items: List<T>)
}

@Transaction
suspend inline fun <reified T> BaseDao<T>.upsert(item: T) {
    if (insert(item) != -1L) return
    update(item)
}

@Transaction
suspend inline fun <reified T> BaseDao<T>.upsert(items: List<T>) {
    val pendingUpdates = insert(items).withIndex()
        .filter { it.value == -1L }
        .map { items[it.index] }

    if (pendingUpdates.isNotEmpty())
        update(pendingUpdates)
}