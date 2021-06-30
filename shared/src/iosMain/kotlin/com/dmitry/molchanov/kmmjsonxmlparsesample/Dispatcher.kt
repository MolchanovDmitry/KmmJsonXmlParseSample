package com.dmitry.molchanov.kmmjsonxmlparsesample

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

actual val uiDispatcher: CoroutineContext = MainDispatcher
actual val ioDispatcher: CoroutineContext = Dispatchers.Default


/**
 * Если мы попытаемся заморозить уже замороженный объект, например, синглтоны,
 * которые считаются замороженными по умолчанию, то получим FreezingException.
 * Чтобы этого не произошло, помечаем синглтоны аннотацией [ThreadLocal],
 * а глобальные переменные [SharedImmutable]:
 */
@ThreadLocal
object MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            try {
                // Для того, чтобы мы могли передавать изменяемые блоки кода и объекты между потоками, нам нужно их замораживать перед передачей
                block.run().freeze()
            } catch (err: Throwable) {
                throw err
            }
        }
    }
}

/*
/**
 * https://github.com/Kotlin/kotlinx.coroutines/issues/462
 * 1. Мы не можем использовать dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(),0.toULong())),
 *      потому что он не привязан ни к одному потоку в Kotlin/Native.
 * 2. Kotlin/Native в отличие от Kotlin/JVM не может шарить корутины между потоками. А также любые изменяемые объекты.
 * 3. Но есть версия с многопоточными корутиными https://github.com/Kotlin/kotlinx.coroutines/blob/native-mt/kotlin-native-sharing.md
 */
private object IODispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(
            dispatch_get_global_queue(
                DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(),
                0.toULong()
            )
        ) {
            try {
                block.run()
            } catch (err: Throwable) {
                throw err
            }
        }
    }
}*/
