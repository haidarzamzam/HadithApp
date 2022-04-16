package com.haidev.hadithapp.di

import com.haidev.hadithapp.BuildConfig
import com.haidev.hadithapp.data.source.remote.provideApi
import com.haidev.hadithapp.data.source.remote.provideCacheInterceptor
import com.haidev.hadithapp.data.source.remote.provideHttpLoggingInterceptor
import com.haidev.hadithapp.data.source.remote.provideMoshiConverter
import org.koin.dsl.module

val remoteModule = module {
    single { provideCacheInterceptor() }
    single { provideHttpLoggingInterceptor() }
    single { provideMoshiConverter() }
    single {
        provideApi(
            BuildConfig.API_URL,
            get()
        )
    }
}
