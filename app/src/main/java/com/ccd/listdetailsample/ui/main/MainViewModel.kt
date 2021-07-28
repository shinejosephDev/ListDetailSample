package com.ccd.listdetailsample.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccd.listdetailsample.business.model.Article
import com.ccd.listdetailsample.business.model.Response
import com.ccd.listdetailsample.data.repository.AppRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    val response: MutableLiveData<Response> = MutableLiveData()

    init {
        getArticles()
    }

    fun getArticles() = viewModelScope.launch {
        fetchArticles()
    }

    private suspend fun fetchArticles() {
        val data = appRepository.getList("all-sections", "7")
        response.postValue(data)
    }
}