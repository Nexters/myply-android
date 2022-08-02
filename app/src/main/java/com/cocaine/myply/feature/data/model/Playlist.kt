package com.cocaine.myply.feature.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Playlist(
    @PrimaryKey val id: String,
    val isLike: Boolean
)

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val playlistId: String,
    val body: String
)

data class PlaylistAndRecord(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "id",
        entityColumn = "playlistId"
    )
    val record: Record
)