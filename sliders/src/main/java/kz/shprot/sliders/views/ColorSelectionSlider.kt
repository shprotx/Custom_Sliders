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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kz.shprot.sliders.common.CustomSliderDefaults
import kz.shprot.sliders.model.CustomSliderColors
import kz.shprot.sliders.model.CustomSliderProperties
import kz.shprot.sliders.theme.Dimensions
import kz.shprot.sliders.util.getColorBySliderPosition
import kz.shprot.sliders.util.toPx
import kotlin.math.roundToInt

@Composable
fun ColorSelectionSlider(
    modifier: Modifier = Modifier,
    gradientList: List<Color>,
    horizontalPaddingDp: Dp = Dimensions.paddingBig,
    colors: CustomSliderColors = CustomSliderDefaults.sliderColors(),
    properties: CustomSliderProperties = CustomSliderDefaults.sliderProperties(),
    isSliderEnabled: Boolean = true,
    customIndicator: @Composable (() -> Unit)? = null,
    onSliderPositionChanged: (Color) -> Unit,
    onDragEnd: () -> Unit,
) {

    val sliderWidthDp = LocalConfiguration.current.screenWidthDp.dp - horizontalPaddingDp * 2
    val sliderWidth = sliderWidthDp.toPx()
    var colorPosition by remember { mutableStateOf(Offset(x = sliderWidth / 2, y = 0f)) }
    val gradientBrush = remember { Brush.horizontalGradient(gradientList) }

    LaunchedEffect(Unit) {
        val color = getColorBySliderPosition(gradientList, 0.5f)
        onSliderPositionChanged(color)
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {


        customIndicator?.let {
            var indicatorWidth by remember { mutableIntStateOf(0) }
            Box(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        indicatorWidth = coordinates.size.width
                    }
                    .offset {
                        val sliderPos = colorPosition.x.roundToInt()
                        val padding = horizontalPaddingDp.toPx().roundToInt()
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
                .background(gradientBrush)
                .pointerInput(sliderWidth, isSliderEnabled) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change: PointerInputChange, _: Float ->
                            if (isSliderEnabled) {
                                val newPosition = change.position.copy(
                                    x = change.position.x
                                        .coerceAtLeast(0f)
                                        .coerceAtMost(sliderWidth)
                                )
                                colorPosition = newPosition
                                val normalizedPosition = newPosition.x / sliderWidth
                                val color = getColorBySliderPosition(gradientList, normalizedPosition)
                                onSliderPositionChanged(color)
                            }
                        },
                        onDragEnd = {
                            if (isSliderEnabled) onDragEnd()
                        },
                    )
                },
            onDraw = {

                drawLine(
                    strokeWidth = properties.knobWidth.toPx(),
                    color = if (isSliderEnabled) colors.knobColor else colors.disabledKnobColor,
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
                    color = if (isSliderEnabled) colors.knobColor else colors.disabledKnobColor,
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
                    color = if (isSliderEnabled) colors.knobColor else colors.disabledKnobColor,
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