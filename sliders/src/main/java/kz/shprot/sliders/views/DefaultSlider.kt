package kz.shprot.sliders.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.shprot.sliders.common.drawIndicatorTriangle
import kz.shprot.sliders.model.SliderColors
import kz.shprot.sliders.util.normalizeSliderValue
import kz.shprot.sliders.util.toPx
import kz.shprot.sliders.util.toTechValue

@Composable
fun DefaultSlider(
    modifier: Modifier = Modifier,
    currentValue: Float,
    minValue: Float,
    maxValue: Float,
    horizontalPaddingDp: Dp,
    colors: SliderColors,
    withIndicator: Boolean,
    isSliderEnabled: Boolean,
    onValueChange: (Float) -> Unit,
    onDragEnd: () -> Unit,
) {

    val sliderWidth = (LocalConfiguration.current.screenWidthDp.dp - horizontalPaddingDp * 2).toPx()
    val techCurrentValue = toTechValue(minValue, maxValue, currentValue, sliderWidth)
    val horizontalPaddingPx = horizontalPaddingDp.toPx()
    val sliderPosition = Offset(x = techCurrentValue, y = 0f)

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        if (withIndicator) {
            Canvas(
                modifier = Modifier
                    .padding(bottom = 3.dp)
                    .fillMaxWidth()
                    .height(5.dp),
                onDraw = {
                    if (isSliderEnabled) {
                        drawIndicatorTriangle(
                            currentPosition = sliderPosition.x + horizontalPaddingPx,
                            color = colors.indicatorColor,
                        )
                    }
                },
            )
        }

        Canvas(
            modifier = Modifier
                .padding(horizontal = horizontalPaddingDp)
                .fillMaxWidth()
                .height(50.dp) // todo unification
                .clip(RoundedCornerShape(10.dp)) // todo unification
                .clipToBounds()
                .pointerInput(sliderWidth) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change: PointerInputChange, _: Float ->
                            val newPosition = change.position.copy(
                                x = change.position.x
                                    .coerceAtLeast(0f)
                                    .coerceAtMost(sliderWidth)
                            )

                            onValueChange(
                                normalizeSliderValue(
                                    min = minValue,
                                    max = maxValue,
                                    pixel = newPosition.x,
                                    canvasWidth = sliderWidth,
                                )
                            )
                        },
                        onDragEnd = onDragEnd,
                    )
                },
            onDraw = {

                /* Фон под слайдером */
                drawRoundRect(
                    color = colors.trackColor,
                    size = Size(
                        width = sliderWidth,
                        height = 50.dp.toPx(), // todo customization
                    ),
                    cornerRadius = CornerRadius(
                        x = 10.dp.toPx(), // todo customization
                        y = 10.dp.toPx(), // todo customization
                    ),
                    topLeft = Offset(
                        x = 0f,
                        y = 0f,
                    )
                )

                /* Градиентный ползунок */
                drawRoundRect(
                    brush = SolidColor(colors.sliderColor), // todo customization
                    size = Size(
                        width = techCurrentValue,
                        height = 50.dp.toPx(), // todo customization
                    ),
                    cornerRadius = CornerRadius(
                        x = 10.dp.toPx(), // todo customization
                        y = 10.dp.toPx(), // todo customization
                    ),
                    topLeft = Offset(
                        x = 0f,
                        y = 0f,
                    )
                )

                /* Knob */
                val xKnob = sliderPosition.x - 13.dp.toPx()
                drawRoundRect(
                    color = colors.knobColor,
                    size = Size(
                        width = 5.dp.toPx(),
                        height = 26.dp.toPx()
                    ),
                    cornerRadius = CornerRadius(
                        x = 2.dp.toPx(),
                        y = 2.dp.toPx(),
                    ),
                    topLeft = Offset(
                        x = if (xKnob < 8.dp.toPx()) 8.dp.toPx() else xKnob,
                        y = 12.dp.toPx(), // todo customization
                    )
                )
            },
        )
    }
}