package com.brisson.di

import com.brisson.Database
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope


internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

// Helper function that provides easy access for Database
fun Scope.fromDatabase(): Database = get()
