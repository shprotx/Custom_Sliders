package kz.shprot.sliders.common

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import kz.shprot.sliders.theme.Dimensions

internal fun DrawScope.drawIndicatorTriangle(
    indicatorSize: Float,
    currentPosition: Float,
    color: Color
) {

    val path = Path().apply {
        moveTo(x = currentPosition, y = indicatorSize)
        lineTo(x = currentPosition + indicatorSize, y = 0f)
        lineTo(x = currentPosition - indicatorSize, y = 0f)
        close()
    }

    drawPath(
        path = path,
        color = color
    )
}


internal fun DrawScope.drawStripes(
    elementWidth: Float,
    elementHeight: Float,
    step: Float,
    cornerRadius: Float,
    xOffset: Float,
    yOffset: Float,
    color: Color,
) {

    val rect = Rect(
        left = xOffset,
        top = yOffset,
        right = elementWidth + xOffset,
        bottom = yOffset + elementHeight
    )
    val clipPath = Path().apply {
        addRoundRect(
            RoundRect(
                rect = rect,
                cornerRadius = CornerRadius(cornerRadius)
            )
        )
    }

    with(clipPath) {
        this@drawStripes.clipPath(path = this) {
            var x = xOffset
            while (x <= elementWidth + elementHeight / 2 + xOffset) {
                drawLine(
                    color = color,
                    start = Offset(
                        x = x,
                        y = 0f
                    ),
                    end = Offset(
                        x = x - elementHeight / 2,
                        y = yOffset + elementHeight
                    ),
                    strokeWidth = Dimensions.strokeWidthSmall.toPx()
                )
                x += step
            }
        }

        drawRoundRect(
            style = Stroke(Dimensions.strokeWidthMedium.toPx()),
            color = color,
            cornerRadius = CornerRadius(cornerRadius),
            size = Size(
                width = elementWidth - 2,
                height = elementHeight - 2
            ),
            topLeft = Offset(
                x = xOffset + 1,
                y = yOffset + 1
            )
        )
    }
}