package com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract  class BaseViewModel(private val coroutineContext: CoroutineContext) {
  protected var scope: ModelScope = ModelScope(coroutineContext)

    fun clear() {
        scope.onCleared()
    }
}

class ModelScope(context: CoroutineContext) : CoroutineScope {
    private var onClearedJob = Job()
    override val coroutineContext: CoroutineContext =  context + onClearedJob

   fun onCleared() {
       onClearedJob.cancel()
   }
}
