package moe.swap.aniapp.data.daos

import androidx.room.*
import moe.swap.aniapp.data.entities.LibraryListEntity
import moe.swap.aniapp.data.entities.relations.ListWithAnime
import moe.swap.aniapp.data.util.BaseDao

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