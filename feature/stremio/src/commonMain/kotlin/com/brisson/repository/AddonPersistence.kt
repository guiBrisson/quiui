package com.brisson.repository

import co.touchlab.kermit.Logger
import com.brisson.api.StremioApi
import com.brisson.model.Addon


interface AddonPersistence {
    suspend fun loadAddons(): List<Addon>
    suspend fun saveAddon(vararg addonBaseUrl: String)
}

fun addonPersistenceInMemory(
    api: StremioApi,
    logger: Logger,
) = object : AddonPersistence {
    private val _addons = mutableListOf<Addon>()

    override suspend fun loadAddons(): List<Addon> = _addons

    override suspend fun saveAddon(vararg addonBaseUrl: String) {
        addonBaseUrl.forEach { baseUrl ->
            val manifest = api.getManifest(baseUrl)
            val addon = Addon(baseUrl, manifest)
            _addons.add(addon)
            logger.i { "Saving addon ${addon.manifest.name}" }
        }
    }
}
