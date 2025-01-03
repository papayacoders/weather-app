package com.example.weathernow.ui.icons

object WeatherIcons {
    // SVG paths for different weather conditions
    val sunny = """
        <svg viewBox="0 0 100 100">
            <circle cx="50" cy="50" r="20" fill="#FFD700"/>
            <g stroke="#FFD700" stroke-width="4">
                <line x1="50" y1="15" x2="50" y2="5"/>
                <line x1="50" y1="95" x2="50" y2="85"/>
                <line x1="15" y1="50" x2="5" y2="50"/>
                <line x1="95" y1="50" x2="85" y2="50"/>
                <line x1="25" y1="25" x2="18" y2="18"/>
                <line x1="75" y1="75" x2="82" y2="82"/>
                <line x1="25" y1="75" x2="18" y2="82"/>
                <line x1="75" y1="25" x2="82" y2="18"/>
            </g>
        </svg>
    """.trimIndent()

    val cloudy = """
        <svg viewBox="0 0 100 100">
            <path d="M25,60 Q40,45 60,60 Q80,75 60,85 Q40,95 25,85 Q10,75 25,60" fill="#E0E0E0"/>
            <path d="M35,40 Q50,25 70,40 Q90,55 70,65 Q50,75 35,65 Q20,55 35,40" fill="#F5F5F5"/>
        </svg>
    """.trimIndent()

    val rainy = """
        <svg viewBox="0 0 100 100">
            <path d="M25,40 Q40,25 60,40 Q80,55 60,65 Q40,75 25,65 Q10,55 25,40" fill="#78909C"/>
            <line x1="30" y1="70" x2="25" y2="85" stroke="#4FC3F7" stroke-width="3"/>
            <line x1="45" y1="70" x2="40" y2="85" stroke="#4FC3F7" stroke-width="3"/>
            <line x1="60" y1="70" x2="55" y2="85" stroke="#4FC3F7" stroke-width="3"/>
        </svg>
    """.trimIndent()
}