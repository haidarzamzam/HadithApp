package com.haidev.hadithapp.data.source.endpoint

import com.haidev.hadithapp.data.model.HadithBooksModel
import com.haidev.hadithapp.data.model.HadithCollectionModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("books")
    fun getListHadithBooksAsync(
    ): Deferred<HadithBooksModel.Response>

    @GET("books/{id_book}")
    fun getListHadithCollectionAsync(
        @Path("id_book") idBook: String,
        @Query("range") range: String
    ): Deferred<HadithCollectionModel.Response>
}