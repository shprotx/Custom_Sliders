package kz.shprot.customsliders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kz.shprot.customsliders.ui.theme.CustomSlidersTheme
import kz.shprot.customsliders.util.roundIfWhole
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.views.DefaultSlider

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

    val horizontalPadding = 15.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {

        /* Default slider section*/

        var defSliderCurrentValue by remember { mutableFloatStateOf(40f) }
        var withScale by remember { mutableStateOf(false) }
        val scaleItems = remember(withScale) {
            if (withScale) listOf("10", "20", "30", "40", "50", "60", "70", "80", "90")
            else null
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
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
                    text = "With scale",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Checkbox(
                    checked = withScale,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF18B79E),
                        uncheckedColor = Color(0xFF18B79E),
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
//                brush = Brush.horizontalGradient(
//                    listOf(
//                        Color(0xFF86E5F5),
//                        Color(0xFFFFC000),
//                        Color(0xFFFF9E58),
//                        Color(0xFFFF634E),
//                    )
//                ),
                scaleItems = scaleItems,
                colors = CustomSliderDefaults.sliderColors(),
                sizes = CustomSliderDefaults.sliderSizes(),
                withIndicator = true,
                isSliderEnabled = true,
                onValueChange = { newValue -> defSliderCurrentValue = newValue },
                onDragEnd = { println("onDragEnd") },
            )

            DrawSliderValues(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding),
                min = 0f,
                max = 100f,
            )
        }
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