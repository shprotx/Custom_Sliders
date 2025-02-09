package kz.shprot.sliders.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

@Immutable
class CustomSliderColors constructor(
    val trackColor: Color,
    val sliderColor: Color,
    val knobColor: Color,
    val indicatorColor: Color,
    val moreThanBaseColor: Color,
    val lessThanBaseColor: Color,
) {

    fun copy(
        trackColor: Color = this.trackColor,
        sliderColor: Color = this.sliderColor,
        knobColor: Color = this.knobColor,
        indicatorColor: Color = this.indicatorColor,
        moreThanBaseColor: Color = this.moreThanBaseColor,
        lessThanBaseColor: Color = this.lessThanBaseColor,
    ) = CustomSliderColors(
        trackColor.takeOrElse { this.trackColor },
        sliderColor.takeOrElse { this.sliderColor },
        knobColor.takeOrElse { this.knobColor },
        indicatorColor.takeOrElse { this.indicatorColor },
        moreThanBaseColor.takeOrElse { this.moreThanBaseColor },
        lessThanBaseColor.takeOrElse { this.lessThanBaseColor },
    )
}