package com.brisson

import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*

actual fun getClientEngine(): HttpClientEngine = CIO.create()
