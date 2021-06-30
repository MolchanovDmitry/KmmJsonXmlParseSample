package com.dmitry.molchanov.kmmjsonxmlparsesample.domain

import com.dmitry.molchanov.kmmjsonxmlparsesample.Constants
import com.dmitry.molchanov.kmmjsonxmlparsesample.entity.Catalog
import com.dmitry.molchanov.kmmjsonxmlparsesample.ioDispatcher
import com.dmitry.molchanov.kmmjsonxmlparsesample.network.XmlRepository
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.XML

class XmlInteractor {
    private val repository = XmlRepository()

    suspend fun getTotalPriceFromXml(): Float = withContext(ioDispatcher) {
        val result = repository.loadData<String>(Constants.XML_URL)
        val catalog = parseResponse(result)
        getTotalPrice(catalog)
    }

    private fun parseResponse(xmlResponse: String?): Catalog {
        val xml = XML {
            policy = JacksonPolicy
        }
        return xml.decodeFromString(xmlResponse!!)
    }

    private fun getTotalPrice(catalog: Catalog): Float {
        return catalog.plants
            .map { it.price.substring(1) }
            .map { it.toFloat() }
            .sum()
    }
}