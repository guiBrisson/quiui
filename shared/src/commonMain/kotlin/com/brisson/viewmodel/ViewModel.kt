package com.brisson.viewmodel

import co.touchlab.kermit.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import androidx.lifecycle.ViewModel as LifecycleViewModel


open class ViewModel: LifecycleViewModel(), KoinComponent {
    protected val logger: Logger by inject { parametersOf(this::class.simpleName) }

    open fun onDispose() {
        logger.d { "Disposing ViewModel" }
        super.onCleared()
    }
}
