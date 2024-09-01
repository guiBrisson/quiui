package com.brisson.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.brisson.ClientWrapper
import com.brisson.api.AddonApi
import com.brisson.api.StremioApi
import com.brisson.api.addonApi
import com.brisson.api.stremioApi
import com.brisson.getClientEngine
import com.brisson.AddonPersistence
import com.brisson.repository.StremioRepository
import com.brisson.addonPersistence
import com.brisson.repository.AddonRepository
import com.brisson.repository.addonRepository
import com.brisson.repository.stremioRepository
import com.brisson.viewmodel.HomeViewModel
import io.ktor.client.HttpClient
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

    includes(dbModule)
}

private val stremioModule = module {
    single<HttpClient> { ClientWrapper(getClientEngine(), log = getWith("StremioHttpClient")).client }

    single<StremioApi> { stremioApi(client = get()) }

    single<AddonApi> { addonApi(client = get()) }

    single<AddonPersistence> { addonPersistence(api = get(), addonService = get()) }

    single<StremioRepository> {
        stremioRepository(
            stremioApi = get(),
            addonPersistence = get(),
            logger = getWith("StremioRepository"),
        )
    }

    single<AddonRepository> {
        addonRepository(
            addonApi = get(),
            addonPersistence = get(),
            logger = getWith("AddonRepository"),
        )
    }
}

private val viewModelModule = module {
    single<HomeViewModel> { HomeViewModel(stremioRepository = get(), addonRepository = get()) }
}
