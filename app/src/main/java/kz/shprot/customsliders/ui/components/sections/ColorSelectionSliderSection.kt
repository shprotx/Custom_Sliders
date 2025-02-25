package kz.shprot.customsliders.ui.components.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import kz.shprot.customsliders.R
import kz.shprot.customsliders.ui.components.CustomIndicator
import kz.shprot.customsliders.ui.theme.Dimensions
import kz.shprot.customsliders.ui.theme.PaletteBlueDark
import kz.shprot.customsliders.ui.theme.PaletteBlueLight
import kz.shprot.customsliders.ui.theme.PaletteBlueViolet
import kz.shprot.customsliders.ui.theme.PaletteGreen
import kz.shprot.customsliders.ui.theme.PaletteOrange
import kz.shprot.customsliders.ui.theme.PaletteRed
import kz.shprot.customsliders.ui.theme.PaletteYellow
import kz.shprot.customsliders.ui.theme.SurfaceBlack
import kz.shprot.customsliders.util.toHex
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.views.ColorSelectionSlider

@Composable
internal fun ColorSelectionSliderSection(
    horizontalPadding: Dp,
) {

    val context = LocalContext.current
    val rgbGradient = remember {
        listOf(
            PaletteRed,
            PaletteOrange,
            PaletteYellow,
            PaletteGreen,
            PaletteBlueLight,
            PaletteBlueDark,
            PaletteBlueViolet,
        )
    }
    var currentColor: Color? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .height(Dimensions.sectionHeight),
        verticalArrangement = Arrangement.Bottom
    ) {

        ColorSelectionSlider(
            modifier = Modifier,
            gradientList = rgbGradient,
            horizontalPaddingDp = horizontalPadding,
            properties = CustomSliderDefaults.sliderProperties(),
            customIndicator = {
                CustomIndicator(
                    value = currentColor?.toHex()
                        ?: context.getString(R.string.undefined)
                )
            },
            onSliderPositionChanged = { newColor ->
                currentColor = newColor
            },
            onDragEnd = {
                // todo something useful
            },
        )

        Box(
            modifier = Modifier
                .padding(
                    horizontal = horizontalPadding,
                    vertical = Dimensions.paddingMedium,
                )
                .width(Dimensions.colorIndicatorBoxWidth)
                .height(Dimensions.colorIndicatorBoxHeight)
                .clip(RoundedCornerShape(Dimensions.cornerRadiusMedium))
                .background(currentColor ?: SurfaceBlack),
        )
    }
}