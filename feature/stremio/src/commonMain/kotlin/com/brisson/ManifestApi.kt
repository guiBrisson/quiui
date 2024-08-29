package com.brisson

import com.brisson.model.Manifest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

interface ManifestApi {
    suspend fun getManifest(addonBaseUrl: String): Manifest
}

fun manifestApi(
    client: HttpClient,
) = object : ManifestApi {
    override suspend fun getManifest(addonBaseUrl: String): Manifest =
        client.get("https://$addonBaseUrl/manifest.json").body()
}
