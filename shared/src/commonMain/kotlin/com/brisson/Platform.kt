package com.brisson

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform