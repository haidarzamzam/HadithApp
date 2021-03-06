package com.haidev.hadithapp.ui.screen.splash

import android.content.Intent
import android.os.Bundle
import com.haidev.hadithapp.R
import com.haidev.hadithapp.databinding.ActivitySplashBinding
import com.haidev.hadithapp.ui.base.BaseActivity
import com.haidev.hadithapp.ui.screen.hadithbooks.HadithBooksActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    SplashNavigator {

    private val splashViewModel: SplashViewModel by inject()
    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        splashViewModel.navigator = this
    }

    override fun setLayout(): Int = R.layout.activity_splash

    override fun getViewModels(): SplashViewModel = splashViewModel

    override fun onResume() {
        super.onResume()
        launch {
            splashViewModel.displaySplashAsync().await()
        }
    }

    override fun navigateToListBooks() {
        val intent = Intent(this@SplashActivity, HadithBooksActivity::class.java)
        startActivity(intent)
        finish()
    }
}