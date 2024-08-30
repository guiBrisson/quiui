package com.brisson

import com.brisson.api.stremioApi
import com.brisson.mock.mockManifestJson
import com.brisson.mock.mockManifestObject
import com.brisson.mock.mockSearchResponseJson
import com.brisson.mock.mockSearchResponseObject
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class StremioApiTest {
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
        val stremioApi = stremioApi(clientWrapper.client)

        assertEquals(
            expected = stremioApi.getManifest("addon-base-url.com"),
            actual = mockManifestObject,
            message = "Could not get manifest with success",
        )
    }

    @Test
    fun `Get Search Success`() = runTest {
        val engine = MockEngine {
            respond(
                content = mockSearchResponseJson,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }
        val clientWrapper = ClientWrapper(engine)
        val stremioApi = stremioApi(clientWrapper.client)

        assertEquals(
            expected = stremioApi.search("good"),
            actual = mockSearchResponseObject,
            message = "Could not get Search with success",
        )
    }
}
