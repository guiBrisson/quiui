package com.brisson.repository

import com.brisson.api.StremioApi
import com.brisson.model.Addon


interface AddonPersistence {
    suspend fun loadAddons(): List<Addon>
    suspend fun saveAddon(addonBaseUrl: String)
}

fun addonPersistenceInMemory(
    api: StremioApi
) = object : AddonPersistence {
    private val _addons = mutableListOf<Addon>()

    override suspend fun loadAddons(): List<Addon> = _addons

    override suspend fun saveAddon(addonBaseUrl: String) {
        val manifest = api.getManifest(addonBaseUrl)
        _addons.add(Addon(addonBaseUrl, manifest))
    }
}
