package com.brisson.viewmodel

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent

open class ViewModel: KoinComponent {
    protected open val viewmodelScope = MainScope()

    open fun onDispose() {
        viewmodelScope.cancel()
    }
}
