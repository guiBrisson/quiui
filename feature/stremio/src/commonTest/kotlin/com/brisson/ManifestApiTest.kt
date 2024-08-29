package com.brisson

import com.brisson.model.BehaviorHints
import com.brisson.model.Manifest
import com.brisson.model.ResObject
import com.brisson.model.ResString
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ManifestApiTest {
    @Test
    fun `Get Manifest Success`() = runTest {
        val engine = MockEngine {
            respond(
                content = mockManifestJson,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }
        val clientWrapper = ClientWrapper(engine)
        val manifestApi = manifestApi(clientWrapper.client)

        assertEquals(
            expected = manifestApi.getManifest("addon-base-url.com"),
            actual = mockManifestObject,
            message = "Could not get manifest with success",
        )
    }
}

private val mockManifestObject = Manifest(
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

private val mockManifestJson = ByteReadChannel(
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
    }"""
)
