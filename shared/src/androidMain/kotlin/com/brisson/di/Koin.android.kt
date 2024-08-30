package com.brisson.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.brisson.db.QuiuiDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = QuiuiDatabase.Schema,
            context = get(),
            name = "quiui.db",
        )
    }
}
