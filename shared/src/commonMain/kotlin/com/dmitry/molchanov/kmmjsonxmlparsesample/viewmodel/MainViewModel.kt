package com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel

import com.dmitry.molchanov.kmmjsonxmlparsesample.Greeting
import com.dmitry.molchanov.kmmjsonxmlparsesample.JsonInteractor
import com.dmitry.molchanov.kmmjsonxmlparsesample.uiDispatcher
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel(uiDispatcher) {

    val jsonTitle: LiveData<String> = LiveData()
    val greeting = Greeting().greeting()

    private val interactor = JsonInteractor.instance

    fun loadTitleFromJson() {
        scope.launch {
            val result = interactor.getTitleFromJson()
            jsonTitle.value = result
        }
    }
}