package com.example.weathernow.ui.Screens

import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.example.weathernow.data.Entity.WeatherResponse

import kotlin.math.roundToInt
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.Compress

import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathernow.data.ResourceState.ResourceState
import com.example.weathernow.ui.ViewModel.WeatherViewModel

@Composable
fun WeatherContent(weather: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main Weather Display
        WeatherMainCard(weather)

        Spacer(modifier = Modifier.height(24.dp))

        // Detailed Weather Grid
        WeatherDetailsGrid(weather)
    }
}

@Composable
fun WeatherMainCard(weather: WeatherResponse) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp)),
        color = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Weather Icon based on condition
            WeatherIcon(weather.weather.firstOrNull()?.main ?: "Clear")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${weather.main.temp.roundToInt()}°",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = weather.weather.firstOrNull()?.description?.capitalize() ?: "",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TemperatureInfo(
                    label = "Feels like",
                    value = "${weather.main.feels_like.roundToInt()}°"
                )
                TemperatureInfo(
                    label = "Low",
                    value = "${(weather.main.temp - 5).roundToInt()}°" // Example calculation
                )
                TemperatureInfo(
                    label = "High",
                    value = "${(weather.main.temp + 5).roundToInt()}°" // Example calculation
                )
            }
        }
    }
}

@Composable
fun WeatherDetailsGrid(weather: WeatherResponse) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.WaterDrop,
                value = "${weather.main.humidity}%",
                label = "Humidity"
            )
            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Air,
                value = "${weather.wind.speed} m/s",
                label = "Wind"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Compress,
                value = "${weather.main.pressure} hPa",
                label = "Pressure"
            )
            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Visibility,
                value = "10 km", // Add visibility to your model if available
                label = "Visibility"
            )
        }
    }
}

@Composable
fun WeatherIcon(condition: String) {
    val icon = when (condition.lowercase()) {
        "clear" -> Icons.Outlined.WbSunny
        "clouds" -> Icons.Outlined.Cloud
        "rain" -> Icons.Outlined.WaterDrop
        "snow" -> Icons.Outlined.AcUnit
        else -> Icons.Outlined.WbSunny
    }

    Icon(
        imageVector = icon,
        contentDescription = condition,
        modifier = Modifier.size(120.dp),
        tint = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
fun TemperatureInfo(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun WeatherDetailCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    value: String,
    label: String
) {
    Surface(
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        color = MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val cityName by viewModel.cityName.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBarEnhanced(
                value = cityName,
                onValueChange = { viewModel.updateCity(it) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedContent(
                targetState = weatherState,
                transitionSpec = {
                    fadeIn() + slideInVertically() togetherWith fadeOut() + slideOutVertically()
                }
            ) { state ->
                when (state) {
                    is ResourceState.Loading -> {
                        LoadingIndicator()
                    }
                    is ResourceState.Success -> {
                        WeatherContent(weather = state.data)
                    }
                    is ResourceState.Error -> {
                        ErrorContent(message = state.error.toString())
                    }
                }
            }
        }
    }
}
@Composable
fun SearchBarEnhanced(
    value: String,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Search city") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            interactionSource = interactionSource  // Add this line
        )
    }
}
//
//@Composable
//fun com.example.weathernow.ui.Screens.WeatherContent(weather: WeatherResponse) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // City Name
//        Text(
//            text = weather.name,
//            style = MaterialTheme.typography.headlineMedium,
//            fontWeight = FontWeight.Bold
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Weather Icon and Temperature
//        WeatherMainInfo(weather)
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Weather Details Cards
//        com.example.weathernow.ui.Screens.WeatherDetailsGrid(weather)
//    }
//}
//
//@Composable
//fun WeatherMainInfo(weather: WeatherResponse) {
//    Surface(
//        modifier = Modifier
//            .clip(RoundedCornerShape(24.dp))
//            .fillMaxWidth(),
//        color = MaterialTheme.colorScheme.primaryContainer,
//        tonalElevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier.padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "${weather.main.temp.roundToInt()}°",
//                style = MaterialTheme.typography.displayLarge,
//                color = MaterialTheme.colorScheme.onPrimaryContainer
//            )
//
//            Text(
//                text = weather.weather.firstOrNull()?.description?.capitalize() ?: "",
//                style = MaterialTheme.typography.titleLarge,
//                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
//            )
//        }
//    }
//}
//
//@Composable
//fun com.example.weathernow.ui.Screens.WeatherDetailsGrid(weather: WeatherResponse) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        com.example.weathernow.ui.Screens.WeatherDetailCard(
//            modifier = Modifier.weight(1f),
//            icon = Icons.Outlined.WaterDrop,
//            value = "${weather.main.humidity}%",
//            label = "Humidity"
//        )
//
//        com.example.weathernow.ui.Screens.WeatherDetailCard(
//            modifier = Modifier.weight(1f),
//            icon = Icons.Outlined.Air,
//            value = "${weather.wind.speed} m/s",
//            label = "Wind"
//        )
//    }
//}
//
//@Composable
//fun com.example.weathernow.ui.Screens.WeatherDetailCard(
//    modifier: Modifier = Modifier,
//    icon: ImageVector,
//    value: String,
//    label: String
//) {
//    Surface(
//        modifier = modifier
//            .clip(RoundedCornerShape(16.dp)),
//        color = MaterialTheme.colorScheme.secondaryContainer,
//        tonalElevation = 2.dp
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = label,
//                tint = MaterialTheme.colorScheme.onSecondaryContainer,
//                modifier = Modifier.size(32.dp)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = value,
//                style = MaterialTheme.typography.titleLarge,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.onSecondaryContainer
//            )
//
//            Text(
//                text = label,
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
//            )
//        }
//    }
//}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ErrorContent(message: String) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.errorContainer,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        }
    }
}