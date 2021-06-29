package com.dmitry.molchanov.kmmjsonxmlparsesample.db

import com.dmitry.molchanov.kmmjsonxmlparsesample.PropTable


class RepositoryDb(private val db: AppDatabase) {

    var propValue: String?
        get() {
            return db.dbSampleQueries.select(SOME_PROP_NAME).executeAsList().firstOrNull()
        }
        set(value) {
            value ?: return
            db.dbSampleQueries.insert(PropTable(propValue = value, propName = SOME_PROP_NAME))
        }

    companion object {
        const val SOME_PROP_NAME = "SOME_PROP_NAME"
    }
}