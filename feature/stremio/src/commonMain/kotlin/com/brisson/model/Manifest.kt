package com.brisson.model

import kotlinx.serialization.Serializable

@Serializable
data class Manifest(
    val id: String,
    val name: String,
    val description: String,
    val logo: String,
    val resources: List<ResourceType>?,
    val types: List<String>,
    val version: String,
    val catalogs: List<Catalog>?,
    val behaviorHints: BehaviorHints?,
)

/**
 * Generates a map of search URLs for all catalogs in the manifest based on the given query.
 *
 * This function iterates through the catalogs in the manifest, constructs search URLs for each
 * catalog using the provided base URL and search query, and returns a map where the keys are
 * descriptive titles of the catalogs and the values are the corresponding search URLs.
 *
 * @param addonBaseUrl The base URL used to construct the search URLs for the catalogs.
 * @param query The search query string used to generate the search URLs.
 * @return A map where the keys are titles representing the catalog type and name, and the values
 * are the search URLs. Returns `null` if there are no catalogs or if no URLs could be generated.
 */
fun Manifest.searchUrls(addonBaseUrl: String, query: String): Map<String, String>? {
    return catalogs
        ?.mapNotNull { catalog ->
            catalog.searchUrl(addonBaseUrl, query)?.let { url ->
                val title = "${catalog.type} - ${this.name}: ${catalog.name}"
                mapOf(Pair(title, url))
            }
        }
        ?.flatMap { it.entries }
        ?.associate { it.toPair() }
}

