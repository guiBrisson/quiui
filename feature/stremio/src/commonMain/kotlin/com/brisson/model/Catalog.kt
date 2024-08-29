package com.brisson.model

import kotlinx.serialization.Serializable

@Serializable
data class Catalog(
    val id: String,
    val name: String,
    val type: String,
    val genres: List<String>?,
    val extra: List<Extra>?,
    val extraSupported: List<String>?,
) {
    @Serializable
    data class Extra(
        val name: String,
        val isRequired: Boolean?,
        val options: List<String>?,
    )
}

fun Catalog.endpointUrl(addonBaseUrl: String): String =
    "https://$addonBaseUrl/catalog/$type/$id.json"
