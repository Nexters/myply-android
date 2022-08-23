package com.cocaine.myply.feature.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class UserMemoList(val code: Int, val data: Memos?, val message: String?)

data class Memos(val memos: List<MemoResponse>)

@Parcelize
data class MemoResponse(
    val memoID: String,
    val thumbnailURL: String,
    val title: String,
    val keywords: List<String>,
    val body: String
) : Parcelable

data class MemoUpdate(val body: String)