package com.haidev.hadithapp.ui.screen.hadithcollection

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.hadithapp.data.model.HadithCollectionModel
import com.haidev.hadithapp.data.model.Resource
import com.haidev.hadithapp.data.source.repository.HadithRepository
import com.haidev.hadithapp.ui.base.BaseViewModel
import com.haidev.hadithapp.util.ErrorUtil
import kotlinx.coroutines.launch

class HadithCollectionViewModel(
    private val repository: HadithRepository,
    application: Application
) :
    BaseViewModel<HadithCollectionNavigator>(application) {

    private val _listHadithCollection = MutableLiveData<Resource<HadithCollectionModel.Response>>()
    val listHadithCollection: MutableLiveData<Resource<HadithCollectionModel.Response>>
        get() = _listHadithCollection

    fun getListHadithCollection(idBook: String, range: String) {
        viewModelScope.launch {
            _listHadithCollection.postValue(Resource.loading(null))
            try {
                val response = repository.getHadithCollection(idBook, range)
                _listHadithCollection.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _listHadithCollection.postValue(
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