package com.haidev.hadithapp.ui.screen.hadithbooks

import com.haidev.hadithapp.data.model.HadithBooksModel

interface HadithBooksNavigator {
    fun navigateToHadithCollection(data: HadithBooksModel.Response.Data)
}