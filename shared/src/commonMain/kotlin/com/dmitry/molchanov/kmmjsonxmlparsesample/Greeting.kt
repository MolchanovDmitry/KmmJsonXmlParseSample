package com.dmitry.molchanov.kmmjsonxmlparsesample

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    /*init {
        val xml = XML { indent = 2 }
    }*/
}