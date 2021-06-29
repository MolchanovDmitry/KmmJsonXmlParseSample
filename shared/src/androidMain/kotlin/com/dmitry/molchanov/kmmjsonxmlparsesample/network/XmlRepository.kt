package com.dmitry.molchanov.kmmjsonxmlparsesample.network

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*

class XmlRepository {

    val client = HttpClient {
        Json {
            accept(ContentType.Application.Xml)
        }
    }

    suspend inline fun <reified T> loadData(url: String): T? {
        return client.get<T>(url)
    }
}