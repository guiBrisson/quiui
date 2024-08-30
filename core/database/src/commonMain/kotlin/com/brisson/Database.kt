package com.brisson

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import com.brisson.db.QuiuiDatabase
import com.brisson.service.addonService
import kotlinx.coroutines.CoroutineDispatcher

class Database(
    private val sqlDriver: SqlDriver,
    private val log: Logger,
    private val dispatcher: CoroutineDispatcher,
) {
    private val scope: DatabaseScope = object : DatabaseScope {
        override val databaseRef: QuiuiDatabase = QuiuiDatabase(
            driver =  sqlDriver,
        )
        override val logger: Logger = log
        override val backgroundDispatcher: CoroutineDispatcher = dispatcher
    }

    val addonService = scope.addonService()
}

interface DatabaseScope {
    val databaseRef: QuiuiDatabase
    val logger: Logger
    val backgroundDispatcher: CoroutineDispatcher
}
