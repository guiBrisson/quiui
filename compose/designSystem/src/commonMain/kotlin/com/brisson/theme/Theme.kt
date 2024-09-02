package com.brisson.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun QuiuiTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = InterTypography(),
        content = content,
    )
}
