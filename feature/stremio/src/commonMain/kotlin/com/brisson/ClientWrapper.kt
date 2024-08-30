package com.brisson

import co.touchlab.kermit.Logger as KermitLogger
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import io.ktor.client.plugins.logging.Logger as KtorLogger
import kotlinx.serialization.json.Json


class ClientWrapper(engine: HttpClientEngine, log: KermitLogger) : AutoCloseable {
    @OptIn(ExperimentalSerializationApi::class)
    val client: HttpClient = HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = object : KtorLogger {
                override fun log(message: String) {
                    log.v { message }
                }
            }

            level = LogLevel.INFO
        }
    }

    override fun close() = client.close()
}

expect fun getClientEngine(): HttpClientEngine
