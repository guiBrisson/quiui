package com.brisson.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.brisson.resources.Res
import com.brisson.resources.inter_bold
import com.brisson.resources.inter_light
import com.brisson.resources.inter_medium
import com.brisson.resources.inter_regular
import com.brisson.resources.inter_semiBold
import com.brisson.resources.inter_thin
import org.jetbrains.compose.resources.Font

@Composable
fun InterTypography(): Typography = Typography(
    displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = InterFontFamily()),
    displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = InterFontFamily()),
    displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = InterFontFamily()),
    headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = InterFontFamily()),
    headlineMedium =MaterialTheme.typography.headlineMedium.copy(fontFamily = InterFontFamily()),
    headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = InterFontFamily()),
    titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = InterFontFamily()),
    titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = InterFontFamily()),
    titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = InterFontFamily()),
    bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = InterFontFamily()),
    bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = InterFontFamily()),
    bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = InterFontFamily()),
    labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = InterFontFamily()),
    labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = InterFontFamily()),
    labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = InterFontFamily()),
)

@Composable
private fun InterFontFamily() = FontFamily(
    Font(Res.font.inter_thin, FontWeight.Thin),
    Font(Res.font.inter_light, FontWeight.Light),
    Font(Res.font.inter_regular, FontWeight.Normal),
    Font(Res.font.inter_medium, FontWeight.Medium),
    Font(Res.font.inter_semiBold, FontWeight.SemiBold),
    Font(Res.font.inter_bold, FontWeight.Bold),
)
