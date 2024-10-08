package com.brisson.model

import kotlinx.serialization.Serializable

@Serializable
data class Catalog(
    val id: String,
    val name: String?,
    val type: String,
    val genres: List<String>?,
    val extra: List<Extra>?,
    val extraSupported: List<String>?,
    val extraRequired: List<String>?,
) {
    @Serializable
    data class Extra(
        val name: String,
        val isRequired: Boolean?,
//        val options: List<String>? = null,
    )
}

private fun Catalog.urlBuilder(addonBaseUrl: String, endParam: String? = null): String = StringBuilder().apply {
    append("https://$addonBaseUrl/catalog/$type/$id")
    endParam?.let { append("/$it") }
    append(".json")
}.toString()

private fun Catalog.canSearch(): Boolean = extra?.firstOrNull { ex -> ex.name == "search" } != null

private fun Catalog.hasExtraRequired(): Boolean =
    extraRequired?.isNotEmpty() == true || extra?.firstOrNull { ex -> ex.isRequired == true } != null


fun Catalog.searchUrl(addonBaseUrl: String, query: String): String? =
    if (canSearch()) urlBuilder(addonBaseUrl, endParam = "search=$query") else null

fun Catalog.homeUrl(addonBaseUrl: String): String? =
    if (!hasExtraRequired()) { urlBuilder(addonBaseUrl) } else null

