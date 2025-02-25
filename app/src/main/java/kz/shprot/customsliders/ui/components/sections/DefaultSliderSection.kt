package kz.shprot.customsliders.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import kz.shprot.customsliders.EXAMPLE_MAX_VALUE
import kz.shprot.customsliders.EXAMPLE_MIN_VALUE
import kz.shprot.customsliders.R
import kz.shprot.customsliders.ui.components.CustomIndicator
import kz.shprot.customsliders.ui.components.SliderThresholds
import kz.shprot.customsliders.ui.theme.Dimensions
import kz.shprot.customsliders.util.roundIfWhole
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.views.DefaultSlider

@Composable
internal fun DefaultSliderSection(
    horizontalPadding: Dp,
) {

    val context = LocalContext.current
    var defSliderCurrentValue by remember { mutableFloatStateOf(100f) }

    val scaleItems = remember {
        context.resources
            .getStringArray(R.array.scale_items)
            .toList()
    }

    Column(
        modifier = Modifier
            .height(Dimensions.sectionHeight),
        verticalArrangement = Arrangement.Bottom
    ) {

        DefaultSlider(
            modifier = Modifier,
            currentValue = defSliderCurrentValue,
            minValue = 0f,
            maxValue = 100f,
            horizontalPaddingDp = horizontalPadding,
            scaleItems = scaleItems,
            colors = CustomSliderDefaults.sliderColors(),
            properties = CustomSliderDefaults.sliderProperties(),
            withIndicator = true,
            isSliderEnabled = true,
            customIndicator = {
                CustomIndicator(defSliderCurrentValue.roundIfWhole())
            },
            onValueChange = { newValue -> defSliderCurrentValue = newValue },
            onDragEnd = {
                // todo something useful
            },
        )

        SliderThresholds(
            modifier = Modifier
                .padding(
                    horizontal = horizontalPadding,
                    vertical = Dimensions.paddingMedium,
                ),
            min = EXAMPLE_MIN_VALUE,
            max = EXAMPLE_MAX_VALUE,
        )
    }
}