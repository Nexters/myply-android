package com.cocaine.myply.feature.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cocaine.myply.feature.data.model.Keyword

@Dao
interface KeywordDao {

    @Query("SELECT * FROM keyword")
    suspend fun getAllKeywords(): List<Keyword>

    @Insert
    suspend fun insertKeyword(keyword: Keyword)

    @Delete
    suspend fun deleteKeyword(keyword: Keyword)
}