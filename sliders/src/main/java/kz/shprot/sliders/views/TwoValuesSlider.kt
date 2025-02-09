package kz.shprot.sliders.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.common.drawIndicatorTriangle
import kz.shprot.sliders.common.drawStripes
import kz.shprot.sliders.model.CustomSliderColors
import kz.shprot.sliders.model.CustomSliderProperties
import kz.shprot.sliders.util.normalizeSliderValue
import kz.shprot.sliders.util.toPx
import kz.shprot.sliders.util.toTechValue

@Composable
fun TwoValuesSlider(
    modifier: Modifier = Modifier,
    currentValue: Float,
    baseValue: Float,
    minValue: Float,
    maxValue: Float,
    horizontalPaddingDp: Dp = 15.dp,
    brush: Brush? = null,
    colors: CustomSliderColors = CustomSliderDefaults.sliderColors(),
    properties: CustomSliderProperties = CustomSliderDefaults.sliderProperties(),
    withIndicator: Boolean = false,
    isSliderEnabled: Boolean = true,
    onValueChange: (Float) -> Unit,
    onDragEnd: () -> Unit,
) {

    val sliderWidthDp = LocalConfiguration.current.screenWidthDp.dp - horizontalPaddingDp * 2
    val sliderWidth = sliderWidthDp.toPx()
    val techCurrentValue = toTechValue(minValue, maxValue, currentValue, sliderWidth)
    val techBaseValue = toTechValue(minValue, maxValue, baseValue, sliderWidth)
    val horizontalPaddingPx = horizontalPaddingDp.toPx()
    val sliderPosition = Offset(x = techCurrentValue, y = 0f)
    val currentColor = when (currentValue > baseValue) {
        true -> colors.moreThanBaseColor
        false -> colors.lessThanBaseColor
    }
    val knobColor = when (techCurrentValue > techBaseValue + 5.dp.toPx() + properties.knobHorizontalPadding.toPx()) {
        true -> colors.moreThanBaseColor
        false -> colors.knobColor
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        if (withIndicator) {
            Canvas(
                modifier = Modifier
                    .padding(bottom = 3.dp)
                    .fillMaxWidth()
                    .height(properties.indicatorSize),
                onDraw = {
                    if (isSliderEnabled) {
                        drawIndicatorTriangle(
                            indicatorSize = properties.indicatorSize.toPx(),
                            currentPosition = sliderPosition.x + horizontalPaddingPx,
                            color = currentColor,
                        )
                    }
                },
            )
        }

        Canvas(
            modifier = Modifier
                .padding(horizontal = horizontalPaddingDp)
                .fillMaxWidth()
                .height(properties.sliderHeight)
                .clip(RoundedCornerShape(properties.sliderCornerRadius))
                .clipToBounds()
                .background(colors.trackColor)
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

                /* красные полоски */
                if (sliderPosition.x > techBaseValue) {
                    if (isSliderEnabled)
                        drawStripes(
                            elementWidth = sliderPosition.x,
                            elementHeight = properties.sliderHeight.toPx(),
                            step = properties.stepBetweenStripes.toPx(),
                            cornerRadius = properties.sliderCornerRadius.toPx(),
                            xOffset = 0f,
                            yOffset = 0f,
                            color = colors.moreThanBaseColor,
                        )
                }

                /* градиентный ползунок */
                if (sliderPosition.x >= techBaseValue) {
                    drawRoundRect(
                        brush = brush ?: SolidColor(colors.sliderColor),
                        size = Size(
                            width = techBaseValue,
                            height = properties.sliderHeight.toPx(),
                        ),
                        cornerRadius = CornerRadius(
                            x = properties.sliderCornerRadius.toPx(),
                            y = properties.sliderCornerRadius.toPx(),
                        ),
                        topLeft = Offset(
                            x = 0f,
                            y = 0f,
                        )
                    )
                } else {
                    if (isSliderEnabled)
                        drawStripes(
                            elementWidth = techBaseValue,
                            elementHeight = properties.sliderHeight.toPx(),
                            step = properties.stepBetweenStripes.toPx(),
                            cornerRadius = properties.sliderCornerRadius.toPx(),
                            xOffset = 0f,
                            yOffset = 0f,
                            color = colors.lessThanBaseColor,
                        )
                }

                if (sliderPosition.x <= techBaseValue) {
                    drawRoundRect(
                        brush = brush ?: SolidColor(colors.sliderColor),
                        size = Size(
                            width = sliderPosition.x,
                            height = properties.sliderHeight.toPx(),
                        ),
                        cornerRadius = CornerRadius(
                            x = properties.sliderCornerRadius.toPx(),
                            y = properties.sliderCornerRadius.toPx(),
                        ),
                        topLeft = Offset(
                            x = 0f,
                            y = 0f,
                        )
                    )
                }

                /* Knob */
                val xKnob = sliderPosition.x - 5.dp.toPx() - properties.knobHorizontalPadding.toPx()
                drawRoundRect(
                    color = knobColor,
                    size = Size(
                        width = 5.dp.toPx(),
                        height = (properties.sliderHeight - properties.knobVerticalPadding * 2).toPx(),
                    ),
                    cornerRadius = CornerRadius(
                        x = 2.dp.toPx(),
                        y = 2.dp.toPx(),
                    ),
                    topLeft = Offset(
                        x = if (xKnob < properties.knobHorizontalPadding.toPx())
                            properties.knobHorizontalPadding.toPx()
                        else
                            xKnob,
                        y = properties.knobVerticalPadding.toPx(),
                    )
                )
            },
        )
    }
}