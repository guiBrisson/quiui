package com.brisson.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Addon(
    @SerialName("transportUrl") val baseUrl: String,
    val manifest: Manifest,
)
