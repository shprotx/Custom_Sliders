package kz.shprot.customsliders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kz.shprot.customsliders.ui.theme.CustomSlidersTheme
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

        var defSliderCurrentValue by remember { mutableFloatStateOf(60f) }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding),
                text = "$defSliderCurrentValue",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            DefaultSlider(
                modifier = Modifier,
                currentValue = defSliderCurrentValue,
                minValue = 40f,
                maxValue = 80f,
                horizontalPaddingDp = horizontalPadding,
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF86E5F5),
                        Color(0xFFFFC000),
                        Color(0xFFFF9E58),
                        Color(0xFFFF634E),
                    )
                ),
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
                min = 40f,
                max = 80f,
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
            text = "$min",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            text = "$max",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}