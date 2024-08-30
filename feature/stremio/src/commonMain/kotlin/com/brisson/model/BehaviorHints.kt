package com.brisson.model

import kotlinx.serialization.Serializable

@Serializable
data class BehaviorHints(
    val adult: Boolean?,
    val configurable: Boolean?,
    val configurationRequired: Boolean?,
)

fun BehaviorHints.isAdult(): Boolean = adult ?: false
