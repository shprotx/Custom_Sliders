package kz.shprot.sliders.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.shprot.sliders.model.CustomSliderColors
import kz.shprot.sliders.model.CustomSliderSizes

object CustomSliderDefaults {

    @Composable
    fun sliderColors(
        trackColor: Color = Color(0xFF484848),
        sliderColor: Color = Color(0xFF18B79E),
        knobColor: Color = Color.White,
        indicatorColor: Color = Color(0xFFFE644F),
    ): CustomSliderColors = CustomSliderColors(
        trackColor = trackColor,
        sliderColor = sliderColor,
        knobColor = knobColor,
        indicatorColor = indicatorColor,
    )

    @Composable
    fun sliderSizes(
        sliderCornerRadius: Dp = 10.dp,
        knobHorizontalPadding: Dp = 10.dp,
        knobVerticalPadding: Dp = 10.dp,
        sliderHeight: Dp = 50.dp,
        indicatorSize: Dp = 5.dp,
    ): CustomSliderSizes = CustomSliderSizes(
        sliderCornerRadius = sliderCornerRadius,
        knobHorizontalPadding = knobHorizontalPadding,
        knobVerticalPadding = knobVerticalPadding,
        sliderHeight = sliderHeight,
        indicatorSize = indicatorSize,
    )
}