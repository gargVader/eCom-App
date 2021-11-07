package com.example.ecom.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * In the DAO (data access object), you specify SQL queries and associate them with method calls.
 * The compiler checks the SQL and generates queries from convenience annotations for common queries,
 * such as @Insert. Room uses the DAO to create a clean API for your code.
 *
 * The DAO must be an interface or abstract class.
 * By default, all queries must be executed on a separate thread.
 * Room has Kotlin coroutines support. This allows your queries to be annotated with the suspend
 * modifier and then called from a coroutine or from another suspension function.
 */
@Dao
interface ItemDao {

    @Query("SELECT * FROM item_table")
    fun getAllItems(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemList: List<Item>)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM item_table")
    fun deleteAll()

    @Query("SELECT * FROM item_table WHERE LOWER(name) LIKE :searchKeyword or LOWER(synonyms) LIKE :searchKeyword ORDER BY id")
    fun getSearchItems(searchKeyword: String): LiveData<List<Item>>

}