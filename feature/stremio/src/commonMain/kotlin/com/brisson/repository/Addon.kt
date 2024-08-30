package com.brisson.repository

import com.brisson.api.StremioApi
import com.brisson.model.Addon


interface AddonPersistence {
    suspend fun loadAddons(): List<Addon>
    suspend fun saveAddon(addonBaseUrl: String)
}

//TODO: add proper logging
fun addonPersistenceInMemory(
    api: StremioApi
) = object : AddonPersistence {
    private val _addons = mutableListOf<Addon>()

    override suspend fun loadAddons(): List<Addon> = _addons

    override suspend fun saveAddon(addonBaseUrl: String) {
        val manifest = api.getManifest(addonBaseUrl)
        val addon = Addon(addonBaseUrl, manifest)
        _addons.add(addon)
        println("AddonPersistenceInMemory: saving addon ${addon.manifest.name}")
    }
}
