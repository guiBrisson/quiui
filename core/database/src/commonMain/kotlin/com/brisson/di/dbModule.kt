package com.brisson.di

import com.brisson.Database
import com.brisson.service.AddonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.module

val dbModule = module {
    includes(platformDbModule)

    single<Database> {
        Database(
            sqlDriver = get(),
            log = getWith("Database"),
            dispatcher = Dispatchers.IO,
        )
    }

    single<AddonService> { fromDatabase().addonService }
}

expect val platformDbModule: Module

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

// Helper function that provides easy access for Database
fun Scope.fromDatabase(): Database = get()
