package kz.shprot.sliders.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.common.drawIndicatorTriangle
import kz.shprot.sliders.common.drawStripes
import kz.shprot.sliders.model.CustomSliderColors
import kz.shprot.sliders.model.CustomSliderProperties
import kz.shprot.sliders.theme.Dimensions
import kz.shprot.sliders.util.normalizeSliderValue
import kz.shprot.sliders.util.toPx
import kz.shprot.sliders.util.toTechValue
import kotlin.math.roundToInt

@Composable
fun TwoValuesSlider(
    modifier: Modifier = Modifier,
    currentValue: Float,
    baseValue: Float,
    minValue: Float,
    maxValue: Float,
    horizontalPaddingDp: Dp = Dimensions.paddingBig,
    brush: Brush? = null,
    colors: CustomSliderColors = CustomSliderDefaults.sliderColors(),
    properties: CustomSliderProperties = CustomSliderDefaults.sliderProperties(),
    withIndicator: Boolean = false,
    isSliderEnabled: Boolean = true,
    customIndicator: @Composable (() -> Unit)? = null,
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
    val knobColor = when (
        techCurrentValue > techBaseValue +
                properties.knobWidth.toPx() +
                properties.knobHorizontalPadding.toPx()
    ) {
        true -> colors.moreThanBaseColor
        false -> colors.knobColor
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        if (withIndicator && customIndicator == null) {
            Canvas(
                modifier = Modifier
                    .padding(bottom = Dimensions.paddingSmall)
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

        customIndicator?.let {
            var indicatorWidth by remember { mutableIntStateOf(0) }
            Box(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        indicatorWidth = coordinates.size.width
                    }
                    .offset {
                        val sliderPos = sliderPosition.x.roundToInt()
                        val padding = horizontalPaddingPx.roundToInt()
                        val indicatorMiddle = indicatorWidth / 2
                        val targetPosition = sliderPos - indicatorMiddle + padding
                        val position =
                            if (targetPosition < padding)
                                padding
                            else if (targetPosition + indicatorWidth > sliderWidth + padding)
                                sliderWidth.toInt() - indicatorWidth + padding
                            else
                                targetPosition
                        IntOffset(position, 0)
                    },
            ) {
                customIndicator()
            }
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
                val xKnob = sliderPosition.x -
                        properties.knobWidth.toPx() -
                        properties.knobHorizontalPadding.toPx()
                drawRoundRect(
                    color = knobColor,
                    size = Size(
                        width = properties.knobWidth.toPx(),
                        height = (properties.sliderHeight - properties.knobVerticalPadding * 2).toPx(),
                    ),
                    cornerRadius = CornerRadius(
                        x = Dimensions.cornersSmall.toPx(),
                        y = Dimensions.cornersSmall.toPx(),
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