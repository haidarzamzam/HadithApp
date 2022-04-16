package com.haidev.hadithapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

object HadithBooksModel {
    data class Response(
        val code: Int,
        val `data`: List<Data>,
        val error: Boolean,
        val message: String
    ) {
        @Parcelize
        data class Data(
            val available: Int,
            val id: String,
            val name: String
        ) : Parcelable
    }
}
