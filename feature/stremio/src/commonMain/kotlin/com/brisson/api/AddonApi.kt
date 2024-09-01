package com.brisson.api

import com.brisson.model.Addon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface AddonApi {
    suspend fun getAllAddons(): List<Addon>
}

fun addonApi(
    client: HttpClient,
) = object : AddonApi {
    override suspend fun getAllAddons(): List<Addon> =
        client.get("https://api.strem.io/addonscollection.json").body()
}
