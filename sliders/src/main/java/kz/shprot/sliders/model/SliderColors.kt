package kz.shprot.sliders.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

@Immutable
class SliderColors constructor(
    val trackColor: Color,
    val sliderColor: Color,
    val knobColor: Color,
    val indicatorColor: Color,
) {

    fun copy(
        trackColor: Color = this.trackColor,
        sliderColor: Color = this.sliderColor,
        knobColor: Color = this.knobColor,
        indicatorColor: Color = this.indicatorColor,
    ) = SliderColors(
        trackColor.takeOrElse { this.trackColor },
        sliderColor.takeOrElse { this.sliderColor },
        knobColor.takeOrElse { this.knobColor },
        indicatorColor.takeOrElse { this.indicatorColor },
    )
}