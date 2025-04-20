package kz.shprot.customsliders.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import kz.shprot.customsliders.EXAMPLE_MAX_VALUE
import kz.shprot.customsliders.EXAMPLE_MIN_VALUE
import kz.shprot.customsliders.R
import kz.shprot.customsliders.ui.components.SliderThresholds
import kz.shprot.customsliders.ui.theme.BlueLight
import kz.shprot.customsliders.ui.theme.Dimensions
import kz.shprot.customsliders.ui.theme.Orange
import kz.shprot.customsliders.ui.theme.Red
import kz.shprot.customsliders.ui.theme.Yellow
import kz.shprot.customsliders.util.roundIfWhole
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.views.TwoValuesSlider

@Composable
internal fun TwoValuesSliderSection(
    horizontalPadding: Dp,
    isEnabled: Boolean = true,
) {

    var currentValue by remember { mutableFloatStateOf(60f) }
    val baseValue = 80f

    Column(
        modifier = Modifier
            .height(Dimensions.sectionHeight),
        verticalArrangement = Arrangement.Bottom
    ) {

        Row(
            verticalAlignment = Alignment.Bottom,
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding),
                text = baseValue.roundIfWhole(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Text(
                text = stringResource(R.string.current_indicator_value, currentValue.roundIfWhole()),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        TwoValuesSlider(
            modifier = Modifier,
            currentValue = currentValue,
            baseValue = baseValue,
            minValue = EXAMPLE_MIN_VALUE,
            maxValue = EXAMPLE_MAX_VALUE,
            horizontalPaddingDp = horizontalPadding,
            brush = Brush.horizontalGradient(
                listOf(BlueLight, Yellow, Orange, Red)
            ),
            colors = CustomSliderDefaults.sliderColors(),
            properties = CustomSliderDefaults.sliderProperties(),
            withIndicator = true,
            isSliderEnabled = true,
            customIndicator = null,
            onValueChange = { newValue -> currentValue = newValue },
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