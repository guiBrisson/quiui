package com.brisson.repository

import co.touchlab.kermit.Logger
import com.brisson.AddonPersistence
import com.brisson.api.AddonApi
import com.brisson.model.Addon

interface AddonRepository {
    suspend fun loadAllAddons(): List<Addon>
    suspend fun loadInstalledAddons(): List<Addon>
    suspend fun installAddon(vararg addonBaseUrl: String)
}

fun addonRepository(
    addonApi: AddonApi,
    addonPersistence: AddonPersistence,
    logger: Logger,
) = object : AddonRepository {
    override suspend fun loadAllAddons(): List<Addon> =
        addonApi.getAllAddons().also {
            logger.i { "Loading all addons: ${it.size}" }
        }

    override suspend fun loadInstalledAddons(): List<Addon> =
        addonPersistence.loadInstalledAddons().also {
            logger.i { "Loading installed addons: ${it.size}" }
        }

    override suspend fun installAddon(vararg addonBaseUrl: String) =
        addonPersistence.installAddon(*addonBaseUrl).also {
            logger.i { "Installing addons: ${addonBaseUrl.toList()}" }
        }
}
