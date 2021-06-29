package com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel

import com.dmitry.molchanov.kmmjsonxmlparsesample.Greeting
import com.dmitry.molchanov.kmmjsonxmlparsesample.db.AppDatabase
import com.dmitry.molchanov.kmmjsonxmlparsesample.db.RepositoryDb
import com.dmitry.molchanov.kmmjsonxmlparsesample.domain.JsonInteractor
import com.dmitry.molchanov.kmmjsonxmlparsesample.uiDispatcher
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.launch

open class MainViewModel(sqlDriver: SqlDriver) : BaseViewModel(uiDispatcher) {
    private val repositoryDb = RepositoryDb(AppDatabase(sqlDriver))
    val jsonTitle: LiveData<String> = LiveData()
    val dbValue: LiveData<String> = LiveData()

    //val xmlTotalPrice: LiveData<Float> = LiveData()
    val greeting = Greeting().greeting()

    private val interactor = JsonInteractor.instance

    fun loadTitleFromJson() {
        scope.launch {
            jsonTitle.value = interactor.getTitleFromJson()
            //xmlTotalPrice.value = interactor.
        }
    }

    fun setDbValue(newValue: String){
        repositoryDb.propValue = newValue
    }

    fun getDbValue() {
        dbValue.value = repositoryDb.propValue
    }
}