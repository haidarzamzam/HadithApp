package com.haidev.hadithapp.ui.screen.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.haidev.hadithapp.ui.base.BaseViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class SplashViewModel(application: Application) :
    BaseViewModel<SplashNavigator>(application) {

    fun displaySplashAsync(): Deferred<Boolean> {
        return viewModelScope.async {
            delay(2000)
            navigator?.navigateToListBooks()
            return@async true
        }
    }
}