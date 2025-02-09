package kz.shprot.sliders.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kz.shprot.sliders.model.SliderColors

object SliderDefaults {

    @Composable
    fun sliderColors(
        trackColor: Color = Color(0xFF484848),
        sliderColor: Color = Color(0xFF18B79E),
        knobColor: Color = Color.White,
        indicatorColor: Color = Color(0xFFFE644F),
    ): SliderColors = SliderColors(
        trackColor = trackColor,
        sliderColor = sliderColor,
        knobColor = knobColor,
        indicatorColor = indicatorColor,
    )
}