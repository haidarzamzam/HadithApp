package com.haidev.hadithapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

object HadithCollectionModel {
    data class Response(
        val code: Int,
        val `data`: Data,
        val error: Boolean,
        val message: String
    ) {
        data class Data(
            val available: Int,
            val hadiths: List<Hadith>,
            val id: String,
            val name: String,
            val requested: Int
        ) {
            @Parcelize
            data class Hadith(
                val arab: String,
                val id: String,
                val number: Int
            ) : Parcelable
        }
    }
}