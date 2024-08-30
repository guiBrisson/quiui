package com.brisson.model

import kotlinx.serialization.Serializable

@Serializable
data class Manifest(
    val id: String,
    val name: String,
    val description: String,
    val logo: String?,
    val resources: List<ResourceType>?,
    val types: List<String>,
    val version: String,
    val catalogs: List<Catalog>?,
    val behaviorHints: BehaviorHints?,
)

private fun Manifest.generateUrl(
    addonBaseUrl: String,
    urlProvider: (Catalog, String) -> String?
): Map<String, String>? {
    return catalogs?.mapNotNull { catalog ->
        urlProvider(catalog, addonBaseUrl)?.let { url ->
            val title = "${catalog.type} - ${this.name}: ${catalog.name}"
            mapOf(title to url)
        }
    }?.flatMap { it.entries }?.associate { it.toPair() }
}

/**
 * Generates a map of search URLs for all catalogs in the manifest based on the given query.
 *
 * @param addonBaseUrl The base URL used to construct the search URLs for the catalogs.
 * @param query The search query string used to generate the search URLs.
 * @return A map where the keys are titles representing the catalog type and name, and the values
 * are the search URLs. Returns `null` if there are no catalogs or if no URLs could be generated.
 */
fun Manifest.searchUrls(addonBaseUrl: String, query: String): Map<String, String>? {
    return generateUrl(addonBaseUrl) { catalog, baseUrl ->
        catalog.searchUrl(baseUrl, query)
    }
}

/**
 * Generates a map of catalog URLs for all catalogs in the manifest that do not require extras.
 *
 * @param addonBaseUrl The base URL used to construct the search URLs for the catalogs.
 * @return A map where the keys are titles representing the catalog type and name, and the values
 * are the catalog URLs. Returns `null` if there are no catalogs or if no URLs could be generated.
 */
fun Manifest.homeUrls(addonBaseUrl: String): Map<String, String>? {
    return generateUrl(addonBaseUrl) { catalog, baseUrl ->
        catalog.homeUrl(baseUrl)
    }
}
