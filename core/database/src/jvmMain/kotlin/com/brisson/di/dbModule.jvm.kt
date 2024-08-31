package com.brisson.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.brisson.db.QuiuiDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDbModule: Module = module {
    single<SqlDriver> {
        //TODO: does this url work on release builds?
        JdbcSqliteDriver("jdbc:sqlite:./build/quiui.db").also {
            QuiuiDatabase.Schema.create(it)
        }
    }
}
