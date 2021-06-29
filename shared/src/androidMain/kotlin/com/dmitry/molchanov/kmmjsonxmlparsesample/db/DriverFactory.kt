package com.dmitry.molchanov.kmmjsonxmlparsesample.db

import android.content.Context
import com.dmitry.molchanov.kmmjsonxmlparsesample.Constants.DB_NAME
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, DB_NAME)
    }
}