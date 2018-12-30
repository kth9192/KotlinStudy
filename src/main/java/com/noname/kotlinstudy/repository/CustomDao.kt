package com.noname.kotlinstudy.repository

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface CustomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg customRoom: CustomRoom)

    @Delete
    fun delete(customRoom: CustomRoom)

    @Query("SELECT * from CustomRoom")
    fun getAll(): DataSource.Factory<Int, CustomRoom>

}