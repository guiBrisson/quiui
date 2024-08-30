package com.brisson.viewmodel

import co.touchlab.kermit.Logger
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

open class ViewModel: KoinComponent {
    protected val logger: Logger by inject { parametersOf(this::class.simpleName) }
    protected open val viewmodelScope = MainScope()

    open fun onDispose() {
        logger.d { "Disposing ViewModel" }
        viewmodelScope.cancel()
    }
}
