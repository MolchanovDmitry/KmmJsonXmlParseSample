package com.dmitry.molchanov.kmmjsonxmlparsesample.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dmitry.molchanov.kmmjsonxmlparsesample.JsonInteractor
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: JsonInteractor) : ViewModel() {

    fun getTitleFromJson(onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            interactor.getTitleFromJson()
                ?.let { title -> onSuccess(title) }
        }
    }
}

class MainViewModelFactory(
    private val interactor: JsonInteractor
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(interactor) as T
}