package kz.shprot.sliders.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.model.CustomSliderColors
import kz.shprot.sliders.model.CustomSliderProperties
import kz.shprot.sliders.util.toPx

@Composable
fun ColorSelectionSlider(
    modifier: Modifier = Modifier,
    gradientBrush: Brush,
    horizontalPaddingDp: Dp = 15.dp,
    colors: CustomSliderColors = CustomSliderDefaults.sliderColors(),
    properties: CustomSliderProperties = CustomSliderDefaults.sliderProperties(),
    onSliderPositionChanged: (Color) -> Unit,
    onDragEnd: () -> Unit,
) {

    val sliderWidthDp = LocalConfiguration.current.screenWidthDp.dp - horizontalPaddingDp * 2
    val sliderWidth = sliderWidthDp.toPx()
    var colorPosition by remember { mutableStateOf(Offset(x = sliderWidth / 2, y = 0f)) }

    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {

        Canvas(
            modifier = Modifier
                .padding(horizontal = horizontalPaddingDp)
                .fillMaxWidth()
                .height(properties.sliderHeight)
                .clip(RoundedCornerShape(properties.sliderCornerRadius))
                .clipToBounds()
                .background(gradientBrush)
                .pointerInput(sliderWidth) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change: PointerInputChange, _: Float ->
                            val newPosition = change.position.copy(
                                x = change.position.x
                                    .coerceAtLeast(0f)
                                    .coerceAtMost(sliderWidth)
                            )
                            colorPosition = newPosition
                        },
                        onDragEnd = onDragEnd,
                    )
                },
            onDraw = {

                drawLine(
                    strokeWidth = properties.knobWidth.toPx(),
                    color = colors.knobColor,
                    start = Offset(
                        x = colorPosition.x,
                        y = 0f,
                    ),
                    end = Offset(
                        x = colorPosition.x,
                        y = (properties.sliderHeight / 4).toPx()
                    ),
                )

                drawCircle(
                    color = colors.knobColor,
                    radius = (properties.sliderHeight / 4).toPx(),
                    center = Offset(
                        x = colorPosition.x,
                        y = (properties.sliderHeight / 2).toPx()
                    ),
                    style = Stroke(
                        width = properties.knobWidth.toPx(),
                        cap = StrokeCap.Round,
                    )
                )

                drawLine(
                    strokeWidth = properties.knobWidth.toPx(),
                    color = colors.knobColor,
                    start = Offset(
                        x = colorPosition.x,
                        y = (properties.sliderHeight - properties.sliderHeight / 4).toPx(),
                    ),
                    end = Offset(
                        x = colorPosition.x,
                        y = properties.sliderHeight.toPx(),
                    ),
                )
            }
        )
    }
}