package com.haidev.hadithapp.di

import com.haidev.hadithapp.data.source.repository.HadithRepository
import com.haidev.hadithapp.ui.screen.splash.SplashViewModel
import com.haidev.hadithapp.util.ContextProviders
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(androidApplication()) }
}

val apiRepositoryModule = module {
    single { ContextProviders.getInstance() }
    single { HadithRepository(get()) }
}
