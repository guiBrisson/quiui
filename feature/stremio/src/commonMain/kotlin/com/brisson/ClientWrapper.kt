package com.brisson

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class ClientWrapper(engine: HttpClientEngine) : AutoCloseable {
    val client: HttpClient = HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }

        install(Logging)
    }

    override fun close() = client.close()
}

expect fun getClientEngine(): HttpClientEngine
