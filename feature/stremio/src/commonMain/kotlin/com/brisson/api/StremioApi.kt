package com.brisson.api

import com.brisson.model.Manifest
import com.brisson.model.SearchQueryResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

interface StremioApi {
    suspend fun getManifest(addonBaseUrl: String): Manifest
    suspend fun search(url: String): SearchQueryResponse
}

// TODO: set to run with the IO dispatcher
fun stremioApi(
    client: HttpClient,
) = object : StremioApi {
    override suspend fun getManifest(addonBaseUrl: String): Manifest =
        client.get("https://$addonBaseUrl/manifest.json").body()

    override suspend fun search(url: String): SearchQueryResponse =
        client.get(url).body()

}
