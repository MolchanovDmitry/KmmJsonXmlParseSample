package com.dmitry.molchanov.kmmjsonxmlparsesample

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