package com.brisson.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.brisson.ClientWrapper
import com.brisson.Database
import com.brisson.api.StremioApi
import com.brisson.api.stremioApi
import com.brisson.getClientEngine
import com.brisson.repository.AddonPersistence
import com.brisson.repository.Stremio
import com.brisson.repository.addonPersistenceInMemory
import com.brisson.repository.stremio
import com.brisson.service.AddonService
import com.brisson.viewmodel.HomeViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module? = null): KoinApplication = startKoin {
    appModule?.let { modules(it) }
    modules(
        platformModule,
        coreModule,
        stremioModule,
        viewModelModule,
    )
}

expect val platformModule: Module

private val coreModule = module {
    // TODO: Implement more robust native platform logging
    // platformLogWriter() is a relatively simple config option, useful for local debugging. For production
    // uses you *may* want to have a more robust configuration from the native platform. In KaMP Kit,
    // that would likely go into platformModule expect/actual.
    // See https://github.com/touchlab/Kermit
    val baseLogger =
        Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "quiui")
    factory<Logger> { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }

    includes(databaseModule)
}

private val databaseModule = module {
    single<Database> {
        Database(
            sqlDriver = get(),
            log = getWith("Database"),
            dispatcher = Dispatchers.IO,
        )
    }

    single<AddonService> { fromDatabase().addonService }
}

private val stremioModule = module {
    single<HttpClient> { ClientWrapper(getClientEngine(), log = getWith("StremioHttpClient")).client }

    single<StremioApi> { stremioApi(client = get()) }

    single<AddonPersistence> {
        //TODO: replace with a proper persistence implementation
        addonPersistenceInMemory(api = get(), logger = getWith("AddonPersistence"))
    }

    single<Stremio> { stremio(api = get(), addonPersistence = get(), logger = getWith("Stremio")) }
}

private val viewModelModule = module {
    single<HomeViewModel> { HomeViewModel(stremio = get(), addonPersistence = get()) }
}
