package com.brisson.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.brisson.db.QuiuiDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDbModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            schema = QuiuiDatabase.Schema,
            name = "KampkitDb",
        )
    }
}
