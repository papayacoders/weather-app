package com.example.weathernow.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.weathernow.ui.theme.WeatherAppColors.BackgroundLight
import com.example.weathernow.ui.theme.WeatherAppColors.ErrorContainerLight
import com.example.weathernow.ui.theme.WeatherAppColors.ErrorLight
import com.example.weathernow.ui.theme.WeatherAppColors.OnErrorLight
import com.example.weathernow.ui.theme.WeatherAppColors.OnPrimaryLight
import com.example.weathernow.ui.theme.WeatherAppColors.OnSecondaryLight
import com.example.weathernow.ui.theme.WeatherAppColors.OnTertiaryLight
import com.example.weathernow.ui.theme.WeatherAppColors.OutlineLight
import com.example.weathernow.ui.theme.WeatherAppColors.PrimaryContainerLight
import com.example.weathernow.ui.theme.WeatherAppColors.PrimaryLight
import com.example.weathernow.ui.theme.WeatherAppColors.SecondaryContainerLight
import com.example.weathernow.ui.theme.WeatherAppColors.SecondaryLight
import com.example.weathernow.ui.theme.WeatherAppColors.SurfaceLight
import com.example.weathernow.ui.theme.WeatherAppColors.SurfaceVariantLight
import com.example.weathernow.ui.theme.WeatherAppColors.TertiaryContainerLight
import com.example.weathernow.ui.theme.WeatherAppColors.TertiaryLight

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,

    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,

    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,

    background = BackgroundLight,
    surface = SurfaceLight,
    surfaceVariant = SurfaceVariantLight,

    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,

    outline = OutlineLight
)

@Composable
fun WeatherNowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,

    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,

    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,

    background = BackgroundLight,
    surface = SurfaceLight,
    surfaceVariant = SurfaceVariantLight,

    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,

    outline = OutlineLight
)