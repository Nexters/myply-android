package com.cocaine.myply.feature.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Keyword(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)