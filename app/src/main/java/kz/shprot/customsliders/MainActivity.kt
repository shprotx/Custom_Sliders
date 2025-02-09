package kz.shprot.customsliders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.shprot.customsliders.ui.theme.CustomSlidersTheme
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
            .fillMaxSize(),
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
                fontSize = 20.sp,
            )

            DefaultSlider(
                modifier = Modifier,
                currentValue = defSliderCurrentValue,
                minValue = 40f,
                maxValue = 80f,
                horizontalPaddingDp = horizontalPadding,
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
            fontSize = 12.sp,
        )

        Text(
            text = "$max",
            fontSize = 12.sp,
        )
    }
}