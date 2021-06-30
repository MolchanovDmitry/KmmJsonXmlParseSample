package com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel

import android.content.Context
import com.dmitry.molchanov.kmmjsonxmlparsesample.db.DriverFactory
import com.dmitry.molchanov.kmmjsonxmlparsesample.domain.XmlInteractor
import kotlinx.coroutines.launch

class AndroidMainViewModel(context: Context) :
    MainViewModel(sqlDriver = DriverFactory(context).createDriver()) {

    val totalPrice: LiveData<Float> = LiveData()

    private val xmlInteractor = XmlInteractor()

    fun getTotalPriceFromXml() {
        scope.launch {
            totalPrice.value = xmlInteractor.getTotalPriceFromXml()
        }
    }
}