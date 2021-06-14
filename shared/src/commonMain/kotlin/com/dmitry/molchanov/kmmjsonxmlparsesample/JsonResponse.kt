package com.dmitry.molchanov.kmmjsonxmlparsesample

import kotlinx.serialization.Serializable

@Serializable
class JsonResponse(
    val userId: Int,
    val id: Int,
    val title: String
)