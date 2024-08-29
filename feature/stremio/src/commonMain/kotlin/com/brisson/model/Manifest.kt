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
