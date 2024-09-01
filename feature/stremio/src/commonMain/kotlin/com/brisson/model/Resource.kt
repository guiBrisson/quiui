package com.brisson.model

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlin.jvm.JvmInline

@Serializable(with = ResourceTypeSerializer::class)
sealed interface ResourceType

@Serializable
data class ResObject(
    val name: String?,
    val types: List<String>?,
    val idPrefixes: List<String>?,
) : ResourceType

@Serializable
@JvmInline
value class ResString(val value: String) : ResourceType

object ResourceTypeSerializer : JsonContentPolymorphicSerializer<ResourceType>(ResourceType::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ResourceType> {
        return if (element is JsonPrimitive && element.isString) {
            ResString.serializer()
        } else {
            ResObject.serializer()
        }
    }
}
