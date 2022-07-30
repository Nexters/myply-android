package com.cocaine.myply.feature.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.cocaine.myply.feature.data.model.Playlist
import com.cocaine.myply.feature.data.model.PlaylistAndRecord
import com.cocaine.myply.feature.data.model.Record

@Dao
interface PlaylistDao {

    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getAllPlaylistsAndRecords(): List<PlaylistAndRecord>

    @Insert
    suspend fun insertPlaylist(playlist: Playlist)

    @Insert
    suspend fun insertRecord(record: Record)

    @Update
    suspend fun updatePlaylist(playlist: Playlist)

    @Update
    suspend fun updateRecord(record: Record)

    @Transaction
    @Delete
    suspend fun deletePlaylistAndRecord(playlist: Playlist)
}