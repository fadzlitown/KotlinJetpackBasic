package com.inovasiti.kucingapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDao {
    //will insert a list of cats
    //suspend = need to create a new thread to handle the bg operation to insert all the cats
    @Insert
    suspend fun addAll(vararg cats : CatSiam) : List<Long>

    @Query("select * FROM catsiam")
    suspend fun getAllCats() :List<CatSiam>

    @Query("select * FROM catsiam WHERE uuid = :catId")
    suspend fun getCat(catId : Long) : CatSiam

    @Query("DELETE FROM catsiam")
    suspend fun deleteAllCats()

}