package com.cocaine.myply.feature.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cocaine.myply.feature.data.model.Keyword

@Database(entities = [Keyword::class], version = 1)
abstract class MyPlyDatabase : RoomDatabase() {

    abstract fun keywordDao(): KeywordDao
}