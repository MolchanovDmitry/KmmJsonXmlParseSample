package com.dmitry.molchanov.kmmjsonxmlparsesample.db

import com.dmitry.molchanov.kmmjsonxmlparsesample.Constants.DB_NAME
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, DB_NAME)
    }
}