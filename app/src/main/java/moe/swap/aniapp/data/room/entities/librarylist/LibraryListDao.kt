package moe.swap.aniapp.data.room.entities.librarylist

import androidx.room.*
import moe.swap.aniapp.data.room.entities.ListWithAnime
import moe.swap.aniapp.data.room.util.BaseDao

@Dao
interface LibraryListDao : BaseDao<LibraryListEntity> {

    @Query("SELECT * FROM lists WHERE id = :listId")
    fun get(listId: Int): LibraryListEntity

    @Query("SELECT * FROM lists")
    fun getAll(): List<LibraryListEntity>

    @Transaction
    @Query("SELECT * FROM lists")
    fun getAllWithAnime(): List<ListWithAnime>
}