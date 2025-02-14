package kz.shprot.sliders.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kz.shprot.sliders.model.CustomSliderColors
import kz.shprot.sliders.model.CustomSliderProperties
import kz.shprot.sliders.theme.Blue
import kz.shprot.sliders.theme.Dimensions
import kz.shprot.sliders.theme.Gray
import kz.shprot.sliders.theme.Green
import kz.shprot.sliders.theme.Red

object CustomSliderDefaults {

    @Composable
    fun sliderColors(
        trackColor: Color = Gray,
        sliderColor: Color = Green,
        knobColor: Color = Color.White,
        indicatorColor: Color = Red,
        moreThanBaseColor: Color = Red,
        lessThanBaseColor: Color = Blue,
    ): CustomSliderColors = CustomSliderColors(
        trackColor = trackColor,
        sliderColor = sliderColor,
        knobColor = knobColor,
        indicatorColor = indicatorColor,
        moreThanBaseColor = moreThanBaseColor,
        lessThanBaseColor = lessThanBaseColor,
    )

    @Composable
    fun sliderProperties(
        sliderCornerRadius: Dp = Dimensions.cornersMedium,
        knobHorizontalPadding: Dp = Dimensions.paddingMedium,
        knobWidth: Dp = Dimensions.knobWidth,
        knobVerticalPadding: Dp = Dimensions.paddingMedium,
        sliderHeight: Dp = Dimensions.sliderHeight,
        indicatorSize: Dp = Dimensions.indicatorSize,
        stepBetweenStripes: Dp = Dimensions.paddingMedium,
    ): CustomSliderProperties = CustomSliderProperties(
        sliderCornerRadius = sliderCornerRadius,
        knobHorizontalPadding = knobHorizontalPadding,
        knobWidth = knobWidth,
        knobVerticalPadding = knobVerticalPadding,
        sliderHeight = sliderHeight,
        indicatorSize = indicatorSize,
        stepBetweenStripes = stepBetweenStripes,
    )
}