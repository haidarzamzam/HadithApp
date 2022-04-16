package com.haidev.hadithapp.ui.screen.hadithcollection

import com.haidev.hadithapp.data.model.HadithCollectionModel

interface HadithCollectionNavigator {
    fun navigateToShareHadith(data: HadithCollectionModel.Response.Data.Hadith)
}