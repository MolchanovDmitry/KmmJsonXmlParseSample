package com.dmitry.molchanov.kmmjsonxmlparsesample.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}