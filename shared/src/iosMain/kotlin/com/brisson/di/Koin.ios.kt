package com.brisson.di

import com.brisson.viewmodel.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {

}

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getBreedViewModel() = getKoin().get<HomeViewModel>()
}
