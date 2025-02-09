package kz.shprot.sliders.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
class CustomSliderSizes(
    val sliderHeight: Dp,
    val sliderCornerRadius: Dp,
    val knobHorizontalPadding: Dp,
    val knobVerticalPadding: Dp,
    val indicatorSize: Dp,
)