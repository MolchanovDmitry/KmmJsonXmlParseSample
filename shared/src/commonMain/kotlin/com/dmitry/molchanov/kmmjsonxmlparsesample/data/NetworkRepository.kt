package com.dmitry.molchanov.kmmjsonxmlparsesample.data

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class NetworkRepository {

    val client = HttpClient {
        install(JsonFeature) {
            val jsonDecoder = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(jsonDecoder)
        }
    }

    suspend inline fun <reified T> loadData(url: String): T? {
        return client.get<T>(url)
    }
}