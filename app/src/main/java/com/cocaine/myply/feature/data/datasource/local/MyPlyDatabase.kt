package com.cocaine.myply.feature.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cocaine.myply.feature.data.model.Keyword
import com.cocaine.myply.feature.data.model.Playlist
import com.cocaine.myply.feature.data.model.Record

@Database(entities = [Keyword::class, Playlist::class, Record::class], version = 1)
abstract class MyPlyDatabase : RoomDatabase() {

    abstract fun keywordDao(): KeywordDao

    abstract fun playlistDao(): PlaylistDao
}