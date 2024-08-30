package com.brisson

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.brisson.di.initKoin

fun main() = application {
    val state = rememberWindowState(width = 375.dp, height = 737.dp)

    initKoin()

    Window(
        title = "quiui",
        state = state,
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}
