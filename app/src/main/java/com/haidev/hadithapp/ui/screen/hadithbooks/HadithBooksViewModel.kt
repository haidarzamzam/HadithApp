package com.haidev.hadithapp.ui.screen.hadithbooks

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.hadithapp.data.model.HadithBooksModel
import com.haidev.hadithapp.data.model.Resource
import com.haidev.hadithapp.data.source.repository.HadithRepository
import com.haidev.hadithapp.ui.base.BaseViewModel
import com.haidev.hadithapp.util.ErrorUtil
import kotlinx.coroutines.launch

class HadithBooksViewModel(private val repository: HadithRepository, application: Application) :
    BaseViewModel<HadithBooksNavigator>(application) {

    private val _listHadithBooks = MutableLiveData<Resource<HadithBooksModel.Response>>()
    val listHadithBooks: MutableLiveData<Resource<HadithBooksModel.Response>>
        get() = _listHadithBooks

    fun getListHadithBooks() {
        viewModelScope.launch {
            _listHadithBooks.postValue(Resource.loading(null))
            try {
                val response = repository.getHadithBooks()
                _listHadithBooks.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _listHadithBooks.postValue(
                    Resource.error(
                        ErrorUtil.getErrorThrowableMsg(t),
                        null,
                        t
                    )
                )
            }
        }
    }
}