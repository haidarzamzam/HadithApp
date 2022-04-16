package com.haidev.hadithapp.data.source.repository

import com.haidev.hadithapp.data.model.HadithBooksModel
import com.haidev.hadithapp.data.model.HadithCollectionModel
import com.haidev.hadithapp.data.source.endpoint.ApiService

class HadithRepository(
    private val apiService: ApiService
) {
    suspend fun getHadithBooks(
    ): HadithBooksModel.Response {
        return apiService.getListHadithBooksAsync().await()
    }

    suspend fun getHadithCollection(
        idBook: String, range: String
    ): HadithCollectionModel.Response {
        return apiService.getListHadithCollectionAsync(idBook, range).await()
    }
}