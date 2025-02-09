package kz.shprot.sliders.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

internal fun toTechValue(min: Float, max: Float, currentValue: Float, canvasWidth: Float): Float {
    val range = max - min
    val normalizedValue = currentValue - min
    return (normalizedValue / range) * canvasWidth
}

internal fun normalizeSliderValue(min: Float, max: Float, pixel: Float, canvasWidth: Float): Float {
    val range = max - min
    val normalizedPixel = pixel / canvasWidth
    return normalizedPixel * range + min
}

internal fun getColorBySliderPosition(gradientItems: List<Color>, position: Float): Color {
    if (position <= 0) return gradientItems.first()
    if (position >= 1) return gradientItems.last()
    val step = 1f / (gradientItems.size - 1)
    for (i in 0 until gradientItems.size - 1) {
        val startStop = i * step
        val endStop = (i + 1) * step
        if (position in startStop..endStop) {
            val startColor = gradientItems[i]
            val endColor = gradientItems[i + 1]

            val fraction = (position - startStop) / (endStop - startStop)
            return lerp(startColor, endColor, fraction)
        }
    }
    return gradientItems.first()
}