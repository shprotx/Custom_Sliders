package kz.shprot.customsliders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.shprot.customsliders.ui.theme.BlueLight
import kz.shprot.customsliders.ui.theme.CustomSlidersTheme
import kz.shprot.customsliders.ui.theme.Dimensions
import kz.shprot.customsliders.ui.theme.Green
import kz.shprot.customsliders.ui.theme.Orange
import kz.shprot.customsliders.ui.theme.PaletteBlueDark
import kz.shprot.customsliders.ui.theme.PaletteBlueLight
import kz.shprot.customsliders.ui.theme.PaletteBlueViolet
import kz.shprot.customsliders.ui.theme.PaletteGreen
import kz.shprot.customsliders.ui.theme.PaletteOrange
import kz.shprot.customsliders.ui.theme.PaletteRed
import kz.shprot.customsliders.ui.theme.PaletteYellow
import kz.shprot.customsliders.ui.theme.Red
import kz.shprot.customsliders.ui.theme.SurfaceBlack
import kz.shprot.customsliders.ui.theme.Yellow
import kz.shprot.customsliders.util.roundIfWhole
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.views.ColorSelectionSlider
import kz.shprot.sliders.views.DefaultSlider
import kz.shprot.sliders.views.TwoValuesSlider

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomSlidersTheme {
                ExampleContent()
            }
        }
    }
}


@Composable
fun ExampleContent() {
    val horizontalPadding = Dimensions.paddingBig

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {

        DefaultSliderSection(horizontalPadding)

        TwoValuesSliderSection(horizontalPadding)

        ColorSelectionSliderSection(horizontalPadding)
    }
}


@Composable
internal fun DefaultSliderSection(
    horizontalPadding: Dp,
) {

    val context = LocalContext.current
    var defSliderCurrentValue by remember { mutableFloatStateOf(40f) }
    var withScale by remember { mutableStateOf(false) }
    val scaleItems = remember(withScale) {
        if (withScale) context.resources.getStringArray(R.array.scale_items).toList()
        else null
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding),
                text = defSliderCurrentValue.roundIfWhole(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding),
                text = stringResource(R.string.with_scale),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Checkbox(
                checked = withScale,
                colors = CheckboxDefaults.colors(
                    checkedColor = Green,
                    uncheckedColor = Green,
                    checkmarkColor = MaterialTheme.colorScheme.onBackground,
                ),
                onCheckedChange = { withScale = !withScale },
            )
        }

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
            onValueChange = { newValue -> defSliderCurrentValue = newValue },
            onDragEnd = { println("onDragEnd") },
        )

        DrawSliderValues(
            modifier = Modifier
                .padding(horizontal = horizontalPadding),
            min = EXAMPLE_MIN_VALUE,
            max = EXAMPLE_MAX_VALUE,
        )
    }
}


@Composable
internal fun TwoValuesSliderSection(
    horizontalPadding: Dp,
) {

    var currentValue by remember { mutableFloatStateOf(60f) }
    val baseValue = 80f

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
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
            onValueChange = { newValue -> currentValue = newValue },
            onDragEnd = { println("onDragEnd") },
        )

        DrawSliderValues(
            modifier = Modifier
                .padding(horizontal = horizontalPadding),
            min = EXAMPLE_MIN_VALUE,
            max = EXAMPLE_MAX_VALUE,
        )
    }
}


@Composable
internal fun ColorSelectionSliderSection(
    horizontalPadding: Dp,
) {

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
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
    ) {

        Box(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .width(Dimensions.colorIndicatorBoxWidth)
                .height(Dimensions.colorIndicatorBoxHeight)
                .clip(RoundedCornerShape(Dimensions.cornerRadiusMedium))
                .background(currentColor ?: SurfaceBlack),
        )

        ColorSelectionSlider(
            modifier = Modifier,
            gradientList = rgbGradient,
            horizontalPaddingDp = horizontalPadding,
            properties = CustomSliderDefaults.sliderProperties(),
            onSliderPositionChanged = { newColor -> currentColor = newColor},
            onDragEnd = { println("onDragEnd") },
        )
    }
}


@Composable
internal fun DrawSliderValues(
    modifier: Modifier,
    min: Float,
    max: Float
) {

    Row(
       modifier = modifier
           .fillMaxWidth(),
       horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = min.roundIfWhole(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            text = max.roundIfWhole(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}


private const val EXAMPLE_MIN_VALUE = 0f
private const val EXAMPLE_MAX_VALUE = 100f