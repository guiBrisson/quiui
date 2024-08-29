package com.brisson.mock

import com.brisson.model.BehaviorHints
import com.brisson.model.Manifest
import com.brisson.model.ResObject
import com.brisson.model.ResString
import io.ktor.utils.io.*


val mockManifestObject = Manifest(
    id = "com.addon",
    name = "Addon",
    description = "Provides streams",
    catalogs = emptyList(),
    resources = listOf(
        ResString("catalog"),
        ResObject(
            idPrefixes = listOf("tt", "kitsu"),
            name = "stream",
            types = listOf("movie", "series"),
        )
    ),
    types = listOf("movie", "series", "anime", "other"),
    logo = "",
    behaviorHints = BehaviorHints(
        adult = null,
        configurable = true,
        configurationRequired = false,
    ),
    version = "0.0.14",
)

val mockManifestJson = ByteReadChannel(
    """
    {
      "id": "com.addon",
      "version": "0.0.14",
      "name": "Addon",
      "description": "Provides streams",
      "catalogs": [],
      "resources": [
        "catalog",
        {
          "name": "stream",
          "types": [
            "movie",
            "series"
          ],
          "idPrefixes": [
            "tt",
            "kitsu"
          ]
        }
      ],
      "types": [
        "movie",
        "series",
        "anime",
        "other"
      ],
      "background": "",
      "logo": "",
      "behaviorHints": {
        "configurable": true,
        "configurationRequired": false
      }
    }""".trimIndent()
)
