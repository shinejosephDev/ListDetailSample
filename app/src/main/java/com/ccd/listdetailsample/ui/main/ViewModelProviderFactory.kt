package com.ccd.listdetailsample.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ccd.listdetailsample.data.repository.AppRepository

class ViewModelProviderFactory(
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel( appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}