package com.brisson.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


// TODO: implement theme
@Composable
fun QuiuiTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = Colors(
            primary = Color(0xff22C55E),
            primaryVariant = Color(0xff15803d),
            secondary = Color(0xff22C55E),
            secondaryVariant = Color(0xff15803d),
            background = Color(0xff0C0A09),
            surface = Color(0xff0C0A09),
            error = Color(0xffef4444),
            onPrimary = Color(0xfff0fdf4),
            onSecondary = Color(0xfff0fdf4),
            onBackground = Color(0xfffafafa),
            onSurface = Color(0xfffafafa),
            onError = Color(0xfffee2e2),
            isLight = false,
        ),
        content = content,
    )
}