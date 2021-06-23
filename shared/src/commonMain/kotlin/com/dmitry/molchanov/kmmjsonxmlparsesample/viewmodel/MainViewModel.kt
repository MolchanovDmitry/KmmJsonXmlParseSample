package com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel

import com.dmitry.molchanov.kmmjsonxmlparsesample.Greeting
import com.dmitry.molchanov.kmmjsonxmlparsesample.domain.JsonInteractor
import com.dmitry.molchanov.kmmjsonxmlparsesample.uiDispatcher
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel(uiDispatcher) {

    val jsonTitle: LiveData<String> = LiveData()
    //val xmlTotalPrice: LiveData<Float> = LiveData()
    val greeting = Greeting().greeting()

    private val interactor = JsonInteractor.instance

    fun loadTitleFromJson() {
        scope.launch {
            jsonTitle.value = interactor.getTitleFromJson()
            //xmlTotalPrice.value = interactor.
        }
    }
}