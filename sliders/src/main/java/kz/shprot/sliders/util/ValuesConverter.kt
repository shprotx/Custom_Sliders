package kz.shprot.sliders.util

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