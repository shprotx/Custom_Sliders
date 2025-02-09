package kz.shprot.sliders.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SliderScale(
    modifier: Modifier,
    scale: List<String>,
    containerSize: Dp,
    color: Color,
) {

    val k = scale.size % 2
    val cellSize = containerSize / (scale.size + k)
    val itemSize = (containerSize - cellSize) / scale.size

    Box(
        modifier = modifier
            .clipToBounds(),
        contentAlignment = Alignment.Center
    ) {

        LazyRow(
            modifier = Modifier
                .width(containerSize),
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = false,
        ) {

            item {
                Spacer(
                    modifier = Modifier.width(cellSize / 2)
                )
            }

            items(items = scale) {
                Text(
                    modifier = Modifier
                        .width(itemSize),
                    text = it,
                    color = color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                )
            }

            item {
                Spacer(
                    modifier = Modifier.width(cellSize / 2)
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize())
    }
}