package com.brisson.repository

import co.touchlab.kermit.Logger
import com.brisson.api.StremioApi
import com.brisson.model.SearchQueryResponse
import com.brisson.model.searchUrls
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Stremio {
    /**
     * Searches for content across multiple addon catalogs based on the provided query.
     *
     * @param query The search query string used to look for content within the catalogs.
     * @return A map where the keys are catalog titles (as `String`) and the values are the corresponding
     * search results (as `SearchQueryResponse`).
     *
     * Example usage:
     * ```
     * val results = search("some query")
     * results.forEach { (catalogTitle, response) ->
     *     println("Catalog: $catalogTitle, Result: $response")
     * }
     * ```
     */
    fun search(query: String): Flow<Map<String, SearchQueryResponse>>
}

fun stremio(
    api: StremioApi,
    addonPersistence: AddonPersistence,
    logger: Logger,
) = object : Stremio {
    override fun search(query: String): Flow<Map<String, SearchQueryResponse>> = flow {
        addonPersistence.loadAddons().forEach { addon ->
            addon.manifest.searchUrls(addon.baseUrl, query)?.forEach { (title, url) ->
                val response = api.search(url)
                emit(mapOf(title to response))
                logger.i { "Searching \"$query\" with $title: ${response.metas.size} results" }
            }
        }
    }
}
