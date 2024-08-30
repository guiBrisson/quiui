package com.brisson.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.brisson.db.QuiuiDatabase
import com.brisson.viewmodel.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(QuiuiDatabase.Schema, "quiui.db")
    }
}

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getHomeViewModel() = getKoin().get<HomeViewModel>()
}
