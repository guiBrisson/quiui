package com.brisson

import co.touchlab.kermit.Logger
import com.brisson.api.StremioApi
import com.brisson.model.Addon
import com.brisson.model.Manifest
import com.brisson.service.AddonService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json


interface AddonPersistence {
    suspend fun loadInstalledAddons(): List<Addon>
    suspend fun installAddon(vararg addonBaseUrl: String)
}

@OptIn(ExperimentalSerializationApi::class)
fun addonPersistence(
    api: StremioApi,
    addonService: AddonService,
) = object : AddonPersistence {
    private val json = Json { ignoreUnknownKeys = true; explicitNulls = false }

    override suspend fun loadInstalledAddons(): List<Addon> {
        return addonService.getAllAddons().map {
            val baseUrl = it.transportUrl.removePrefix("https://").substringBefore("/")
            val manifest = json.decodeFromString<Manifest>(it.manifest)
            Addon(baseUrl, manifest)
        }
    }

    //TODO: maybe I should check if the addon is already saved before fetching the api?
    override suspend fun installAddon(vararg addonBaseUrl: String) {
        addonBaseUrl.forEach { baseUrl ->
            val serializedManifest = api.getManifestAsText(baseUrl)
            addonService.saveAddon(
                transportUrl = "https://$baseUrl/manifest.json",
                manifestEncoded = serializedManifest,
            )
        }
    }
}

fun addonPersistenceInMemory(
    api: StremioApi,
    logger: Logger,
) = object : AddonPersistence {
    private val _addons = mutableListOf<Addon>()

    override suspend fun loadInstalledAddons(): List<Addon> = _addons

    override suspend fun installAddon(vararg addonBaseUrl: String) {
        addonBaseUrl.forEach { baseUrl ->
            val manifest = api.getManifest(baseUrl)
            val addon = Addon(baseUrl, manifest)
            _addons.add(addon)
            logger.i { "Saving addon ${addon.manifest.name}" }
        }
    }
}
