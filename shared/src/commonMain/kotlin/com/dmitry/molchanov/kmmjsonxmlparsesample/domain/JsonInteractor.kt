package com.dmitry.molchanov.kmmjsonxmlparsesample.domain

import com.dmitry.molchanov.kmmjsonxmlparsesample.Constants
import com.dmitry.molchanov.kmmjsonxmlparsesample.entity.JsonResponse
import com.dmitry.molchanov.kmmjsonxmlparsesample.network.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.native.concurrent.ThreadLocal

class JsonInteractor {

    private val repository = NetworkRepository()

    suspend fun getTitleFromJson(): String? = withContext(Dispatchers.Default) {
        repository.loadData<JsonResponse>(Constants.JSON_URL)?.title
    }

    @ThreadLocal
    companion object {
        val instance = JsonInteractor()
    }
}