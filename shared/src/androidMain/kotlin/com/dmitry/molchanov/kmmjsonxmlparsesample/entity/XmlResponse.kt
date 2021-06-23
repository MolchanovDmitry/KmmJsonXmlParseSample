package com.dmitry.molchanov.kmmjsonxmlparsesample.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("CATALOG")
data class Catalog(
    @SerialName("PLANT")  val plants: List<Plant>
)

@Serializable
data class Plant(
    @SerialName("COMMON") val common: String = "",
    @SerialName("BOTANICAL") val botanical: String = "",
    @SerialName("ZONE") val zone: String = "",
    @SerialName("LIGHT") val light: String = "",
    @SerialName("PRICE") val price: String = "",
    @SerialName("AVAILABILITY") val availability: Int = 0

)