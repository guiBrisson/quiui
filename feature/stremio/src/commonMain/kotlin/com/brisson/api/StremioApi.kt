package com.brisson.api

import com.brisson.model.Manifest
import com.brisson.model.Metas
import com.brisson.model.SearchQueryResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText

interface StremioApi {
    suspend fun getManifest(addonBaseUrl: String): Manifest
    suspend fun getManifestAsText(addonBaseUrl: String): String
    suspend fun search(url: String): SearchQueryResponse
    suspend fun home(url: String): Metas
}

// TODO: set to run with the IO dispatcher
// TODO: should abstract the get functions (eg search and home) to the repository?
fun stremioApi(
    client: HttpClient,
) = object : StremioApi {
    override suspend fun getManifest(addonBaseUrl: String): Manifest =
        client.get("https://$addonBaseUrl/manifest.json").body()

    override suspend fun getManifestAsText(addonBaseUrl: String): String =
        client.get("https://$addonBaseUrl/manifest.json").bodyAsText()

    override suspend fun search(url: String): SearchQueryResponse =
        client.get(url).body()

    override suspend fun home(url: String): Metas = client.get(url).body()

}
