package kz.shprot.sliders.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

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