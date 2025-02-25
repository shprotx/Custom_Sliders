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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kz.shprot.customsliders.ui.components.sections.ColorSelectionSliderSection
import kz.shprot.customsliders.ui.components.sections.DefaultSliderSection
import kz.shprot.customsliders.ui.components.sections.TwoValuesSliderSection
import kz.shprot.customsliders.ui.theme.CustomSlidersTheme
import kz.shprot.customsliders.ui.theme.Dimensions
import kz.shprot.customsliders.ui.theme.Green

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
        verticalArrangement = Arrangement.Center
    ) {

        HorizontalDivider()
        DefaultSliderSection(horizontalPadding)
        HorizontalDivider()
        TwoValuesSliderSection(horizontalPadding)
        HorizontalDivider()
        ColorSelectionSliderSection(horizontalPadding)
        HorizontalDivider()
    }
}


internal const val EXAMPLE_MIN_VALUE = 0f
internal const val EXAMPLE_MAX_VALUE = 100f