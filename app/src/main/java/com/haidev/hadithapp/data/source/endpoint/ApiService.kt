package com.haidev.hadithapp.data.source.endpoint

import com.haidev.hadithapp.data.model.HadithBooksModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("books")
    fun getListHadithBooksAsync(
    ): Deferred<HadithBooksModel.Response>
}