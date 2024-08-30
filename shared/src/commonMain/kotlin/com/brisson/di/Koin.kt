package com.brisson.di

import com.brisson.ClientWrapper
import com.brisson.api.StremioApi
import com.brisson.api.stremioApi
import com.brisson.getClientEngine
import com.brisson.repository.AddonPersistence
import com.brisson.repository.Stremio
import com.brisson.repository.addonPersistenceInMemory
import com.brisson.repository.stremio
import com.brisson.viewmodel.HomeViewModel
import com.brisson.viewmodel.HomeViewState
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module? = null): KoinApplication = startKoin {
    appModule?.let { modules(it) }
    modules(
        stremioModule,
        viewModelModule,
    )
}

expect val platformModule: Module

private val stremioModule = module {
    single<StremioApi> {
        // If there is another api in the :feature:stremio module, the client should also be injected
        val client = ClientWrapper(getClientEngine()).client
        stremioApi(client)
    }
    //TODO: replace with a proper persistence implementation
    single<AddonPersistence> { addonPersistenceInMemory(api = get()) }
    single<Stremio> { stremio(api = get(), addonPersistence = get()) }
}

private val viewModelModule = module {
    single<HomeViewModel> { HomeViewModel(stremio = get(), addonPersistence = get()) }
}
